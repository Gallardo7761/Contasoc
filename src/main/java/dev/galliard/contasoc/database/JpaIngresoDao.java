package dev.galliard.contasoc.database;

import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.database.objects.Ingresos;
import dev.galliard.contasoc.util.Parsers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class JpaIngresoDao implements Dao<Ingresos> {
    private static final EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Optional<Ingresos> get(long id) {
        return Optional.ofNullable(entityManager.find(Ingresos.class, id));
    }

    @Override
    public List<Ingresos> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Ingresos e");
        return query.getResultList();
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
    public void save(Ingresos ingresos) {
        transaction(entityManager -> entityManager.persist(ingresos));
    }

    @Override
    public void update(Ingresos ingresos, String[] params) {
        Integer numeroSocio = Integer.valueOf(params[0]);
        Date fecha = params[1].isEmpty() ? null : Date.valueOf(Parsers.dashDateParserReversed(params[1]));
        String concepto = params[2];
        Double cantidad = Double.valueOf(params[3]);
        TipoPago tipo = TipoPago.valueOf(params[4]);

        ingresos.setNumeroSocio(numeroSocio);
        ingresos.setFecha(fecha);
        ingresos.setConcepto(concepto);
        ingresos.setCantidad(cantidad);
        ingresos.setTipo(tipo.name());

        transaction(entityManager -> entityManager.merge(ingresos));
    }

    @Override
    public void delete(Ingresos ingresos) {
        transaction(entityManager -> entityManager.remove(ingresos));
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

    public static List<Ingresos> getIngresosBySocio(int numeroSocio) {
        Query query = entityManager.createQuery("SELECT e FROM Ingresos e WHERE e.numeroSocio = :numeroSocio");
        query.setParameter("numeroSocio", numeroSocio);
        return query.getResultList();
    }
}
