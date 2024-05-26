package dev.gallardo.contasoc.database;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface Dao<T> {
    Optional<T> get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);

    Object execute(String query, String[] params);

    void transaction(Consumer<EntityManager> consumer);
}
