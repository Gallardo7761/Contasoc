package dev.galliard.contasoc.database;

import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.database.objects.Balance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class JpaBalanceDao implements Dao<Balance> {
    private EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Optional<Balance> get(long id) {
        return Optional.ofNullable(entityManager.find(Balance.class, id));
    }

    @Override
    public List<Balance> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Balance e");
        List<Balance> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // Devuelve null si la lista está vacía
        }
        return resultList;
    }


    @Override
    public void save(Balance balance) {
        transaction(entityManager -> entityManager.persist(balance));
    }

    @Override
    public void update(Balance balance, String[] params) {
        Double inicialBanco = Double.valueOf(params[0]);
        Double inicialCaja = Double.valueOf(params[1]);

        balance.setInicialBanco(inicialBanco);
        balance.setInicialCaja(inicialCaja);

        transaction(entityManager -> entityManager.merge(balance));
    }

    @Override
    public void delete(Balance balance) {
        transaction(entityManager -> entityManager.remove(balance));
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
