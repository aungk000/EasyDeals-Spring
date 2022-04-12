package com.losersclub.easydeals;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class Spring {
    public interface Dao<T> {
        int insert(UUID id, T t);

        default int insert(T t) {
            return insert(UUID.randomUUID(), t);
        }

        List<T> getAll();
        Optional<T> get(UUID id);

        int delete(UUID id);
        int update(UUID id, T t);
    }

    public static class Service<T> {
        private final Dao<T> dao;

        public Service(Dao<T> dao) {
            this.dao = dao;
        }

        public int add(T t) {
            return dao.insert(t);
        }

        public List<T> getAll() {
            return dao.getAll();
        }

        public Optional<T> get(UUID id) {
            return dao.get(id);
        }

        public int delete(UUID id) {
            return dao.delete(id);
        }

        public int update(UUID id, T t) {
            return dao.update(id, t);
        }
    }

    // ? ? ? ...
    public static class Repository<T> implements Dao<T> {
        private final JdbcTemplate template;
        private final String tableName;

        public JdbcTemplate getTemplate() {
            return template;
        }

        public Repository(JdbcTemplate template, String tableName) {
            this.template = template;
            this.tableName = tableName;
        }

        @Override
        public int insert(UUID id, T t) {
            String sql = "INSERT INTO " + tableName + " VALUES " + "(?, ?, ?)";
            return 0;
        }

        @Override
        public List<T> getAll() {
            String sql = "SELECT * FROM " + tableName;
            return null;
        }

        @Override
        public Optional<T> get(UUID id) {
            return Optional.empty();
        }

        @Override
        public int delete(UUID id) {
            String sql = "DELETE FROM " + tableName + " WHERE id = ?";
            return template.update(sql, id);
        }

        @Override
        public int update(UUID id, T t) {
            return 0;
        }
    }

    public static class Controller<T> {
        private final Service<T> service;

        public Controller(Service<T> service) {
            this.service = service;
        }

        public void add(T t) {
            service.add(t);
        }

        public List<T> getAll() {
            return service.getAll();
        }

        public T get(UUID id) {
            return service.get(id).orElseThrow();
        }

        public void delete(UUID id) {
            service.delete(id);
        }

        public void update(UUID id, T t) {
            service.update(id, t);
        }
    }
}
