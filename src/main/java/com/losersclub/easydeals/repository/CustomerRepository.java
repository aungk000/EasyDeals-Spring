package com.losersclub.easydeals.repository;

import com.losersclub.easydeals.Spring;
import com.losersclub.easydeals.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class CustomerRepository extends Spring.Repository<Customer> {

    @Autowired
    public CustomerRepository(JdbcTemplate template) {
        super(template, "customer");
    }

    @Override
    public int insert(UUID id, Customer customer) {
        String sql = "INSERT INTO customer VALUES (?, ?, ?)";
        return getTemplate().update(sql, id, customer.getName(), customer.getEmail());
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT * FROM customer";
        return getTemplate().query(sql, (rs, rowNum) -> {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            return new Customer(UUID.fromString(id), name, email);
        });
    }

    @Override
    public Optional<Customer> get(UUID id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        Customer customer = getTemplate().queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            String _id = rs.getString("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            return new Customer(UUID.fromString(_id), name, email);
        });

        return Optional.ofNullable(customer);
    }

    @Override
    public int update(UUID id, Customer customer) {
        String sql = "UPDATE customer SET name = ?, email = ? WHERE id = ?";
        return getTemplate().update(sql, customer.getName(), customer.getEmail(), id);
    }

    /*private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(UUID id, Customer customer) {
        String sql = "INSERT INTO customer VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, id, customer.getName(), customer.getEmail());
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            return new Customer(UUID.fromString(id), name, email);
        });
    }

    @Override
    public Optional<Customer> get(UUID id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        Customer customer = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            String _id = rs.getString("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            return new Customer(UUID.fromString(_id), name, email);
        });

        return Optional.ofNullable(customer);
    }

    @Override
    public int delete(UUID id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(UUID id, Customer customer) {
        String sql = "UPDATE customer SET name = ?, email = ? WHERE id = ?";
        return jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), id);
    }*/
}
