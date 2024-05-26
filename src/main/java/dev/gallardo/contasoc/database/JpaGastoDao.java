package dev.gallardo.contasoc.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import dev.gallardo.contasoc.Contasoc;
import dev.gallardo.contasoc.common.TipoPago;
import dev.gallardo.contasoc.database.objects.Gastos;
import dev.gallardo.contasoc.util.Parsers;

@SuppressWarnings("unchecked")
public class JpaGastoDao implements Dao<Gastos> {
    private final EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Optional<Gastos> get(long id) {
        return Optional.ofNullable(entityManager.find(Gastos.class, id));
    }

    @Override
    public List<Gastos> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Gastos e");
        return query.getResultList();
    }

    @Override
    public void save(Gastos gastos) {
        transaction(entityManager -> entityManager.persist(gastos));
    }

    @Override
    public void update(Gastos gastos, String[] params) {
        Date fecha = params[0].isEmpty() ? null : Date.valueOf(Parsers.dashDateParserReversed(params[0]));
        String proveedor = params[1];
        String concepto = params[2];
        Double cantidad = Double.valueOf(params[3]);
        String factura = params[4];
        String tipo = TipoPago.valueOf(params[5]).name();

        gastos.setFecha(fecha);
        gastos.setProveedor(proveedor);
        gastos.setConcepto(concepto);
        gastos.setCantidad(cantidad);
        gastos.setFactura(factura);
        gastos.setTipo(tipo);

        transaction(entityManager -> entityManager.merge(gastos));
    }

    @Override
    public void delete(Gastos gastos) {
        transaction(entityManager -> entityManager.remove(gastos));
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
