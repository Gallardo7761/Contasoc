package dev.gallardo.contasoc.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import dev.gallardo.contasoc.Contasoc;
import dev.gallardo.contasoc.common.Estado;
import dev.gallardo.contasoc.common.TipoSocio;
import dev.gallardo.contasoc.database.objects.Socios;
import dev.gallardo.contasoc.util.Parsers;

@SuppressWarnings("unchecked")
public class JpaSocioDao implements Dao<Socios> {
    private final EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Optional<Socios> get(long id) {
        return Optional.ofNullable(entityManager.find(Socios.class, id));
    }

    @Override
    public List<Socios> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Socios e");
        return query.getResultList();
    }

    @Override
    public void save(Socios socios) {
        transaction(entityManager -> entityManager.persist(socios));
    }

    @Override
    public void update(Socios socios, String[] params) {
        Integer numeroSocio = Integer.valueOf(params[0]);
        Integer numeroHuerto = Integer.valueOf(params[1]);
        String nombre = params[2];
        String dni = params[3];
        Integer telefono = Integer.valueOf(params[4]);
        String email = params[5];
        Date fechaDeAlta = params[6].isEmpty() ? null : Date.valueOf(Parsers.dashDateParserReversed(params[6]));
        Date fechaDeEntrega = params[7].isEmpty() ? null : Date.valueOf(Parsers.dashDateParserReversed(params[7]));
        Date fechaDeBaja = params[8].isEmpty() ? null : Date.valueOf(Parsers.dashDateParserReversed(params[8]));
        String notas = params[9];
        TipoSocio tipo = TipoSocio.valueOf(params[10]);

        socios.setNumeroSocio(numeroSocio);
        socios.setNumeroHuerto(numeroHuerto);
        socios.setNombre(nombre);
        socios.setDni(dni);
        socios.setTelefono(telefono);
        socios.setEmail(email);
        socios.setFechaDeAlta(fechaDeAlta);
        socios.setFechaDeEntrega(fechaDeEntrega);
        socios.setFechaDeBaja(fechaDeBaja);
        socios.setNotas(notas);
        socios.setTipo(tipo);
        socios.setEstado(fechaDeBaja == null ? Estado.ACTIVO : Estado.INACTIVO);

        transaction(entityManager -> entityManager.merge(socios));
    }

    @Override
    public Object execute(String query, String[] params) {
        Object result = null;
        Query q = entityManager.createQuery(query);
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i + 1, params[i]);
        }
        try {
            result = q.getSingleResult();
        } catch (Exception e) {
            Contasoc.logger.error("Error executing query: " + e.getMessage());
        }
        return result;
    }

    @Override
    public void delete(Socios socios) {
        transaction(entityManager -> entityManager.remove(socios));
    }

    @Override
    public void transaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
