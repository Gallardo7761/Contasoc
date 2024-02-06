package dev.galliard.contasoc.database;

import dev.galliard.contasoc.common.Estado;
import dev.galliard.contasoc.common.TipoSocio;
import dev.galliard.contasoc.database.objects.Socio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class JpaSocioDao implements Dao<Socio> {
    private EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Optional<Socio> get(long id) {
        return Optional.ofNullable(entityManager.find(Socio.class, id));
    }

    @Override
    public List<Socio> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Socio e");
        return query.getResultList();
    }

    @Override
    public void save(Socio socio) {
        transaction(entityManager -> entityManager.persist(socio));
    }

    @Override
    public void update(Socio socio, String[] params) {
        Integer numeroSocio = Integer.valueOf(params[0]);
        Integer numeroHuerto = Integer.valueOf(params[1]);
        String nombre = params[2];
        String dni = params[3];
        Integer telefono = Integer.valueOf(params[4]);
        String email = params[5];
        Date fechaDeAlta = Date.valueOf(LocalDate.parse(params[6]));
        Date fechaDeEntrega = Date.valueOf(LocalDate.parse(params[7]));
        Date fechaDeBaja = Date.valueOf(LocalDate.parse(params[8]));
        String notas = params[9];
        TipoSocio tipo = TipoSocio.valueOf(params[10]);
        Estado estado = Estado.valueOf(params[11]);

        socio.setNumeroSocio(numeroSocio);
        socio.setNumeroHuerto(numeroHuerto);
        socio.setNombre(nombre);
        socio.setDni(dni);
        socio.setTelefono(telefono);
        socio.setEmail(email);
        socio.setFechaDeAlta(fechaDeAlta);
        socio.setFechaDeEntrega(fechaDeEntrega);
        socio.setFechaDeBaja(fechaDeBaja);
        socio.setNotas(notas);
        socio.setTipo(tipo);
        socio.setEstado(estado);

        transaction(entityManager -> entityManager.merge(socio));
    }

    @Override
    public void delete(Socio socio) {
        transaction(entityManager -> entityManager.remove(socio));
    }

    private void transaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
