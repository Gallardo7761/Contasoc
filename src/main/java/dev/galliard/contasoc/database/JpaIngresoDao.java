package dev.galliard.contasoc.database;

import dev.galliard.contasoc.common.Estado;
import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.common.TipoSocio;
import dev.galliard.contasoc.database.objects.Ingreso;
import dev.galliard.contasoc.database.objects.Socio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class JpaIngresoDao implements Dao<Ingreso> {
    private EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Optional<Ingreso> get(long id) {
        return Optional.ofNullable(entityManager.find(Ingreso.class, id));
    }

    @Override
    public List<Ingreso> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Ingreso e");
        return query.getResultList();
    }

    @Override
    public void save(Ingreso ingreso) {
        transaction(entityManager -> entityManager.persist(ingreso));
    }

    @Override
    public void update(Ingreso ingreso, String[] params) {
        Integer numeroSocio = Integer.valueOf(params[0]);
        Date fecha = Date.valueOf(LocalDate.parse(params[1]));
        String concepto = params[2];
        Double cantidad = Double.valueOf(params[3]);
        TipoPago tipo = TipoPago.valueOf(params[4]);

        ingreso.setNumeroSocio(numeroSocio);
        ingreso.setFecha(fecha);
        ingreso.setConcepto(concepto);
        ingreso.setCantidad(cantidad);
        ingreso.setTipo(tipo.name());

        transaction(entityManager -> entityManager.merge(ingreso));
    }

    @Override
    public void delete(Ingreso ingreso) {
        transaction(entityManager -> entityManager.remove(ingreso));
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
