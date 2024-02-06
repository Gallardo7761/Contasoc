package dev.galliard.contasoc.database;

import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.database.objects.Gasto;
import dev.galliard.contasoc.database.objects.Gasto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class JpaGastoDao implements Dao<Gasto> {
    private EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Optional<Gasto> get(long id) {
        return Optional.ofNullable(entityManager.find(Gasto.class, id));
    }

    @Override
    public List<Gasto> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Gasto e");
        return query.getResultList();
    }

    @Override
    public void save(Gasto gasto) {
        transaction(entityManager -> entityManager.persist(gasto));
    }

    @Override
    public void update(Gasto gasto, String[] params) {
        Date fecha = Date.valueOf(LocalDate.parse(params[0]));
        String proveedor = params[1];
        String concepto = params[2];
        Double cantidad = Double.valueOf(params[3]);
        String factura = params[4];
        String tipo = TipoPago.valueOf(params[5]).name();

        gasto.setFecha(fecha);
        gasto.setProveedor(proveedor);
        gasto.setConcepto(concepto);
        gasto.setCantidad(cantidad);
        gasto.setFactura(factura);
        gasto.setTipo(tipo);

        transaction(entityManager -> entityManager.merge(gasto));
    }

    @Override
    public void delete(Gasto gasto) {
        transaction(entityManager -> entityManager.remove(gasto));
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
