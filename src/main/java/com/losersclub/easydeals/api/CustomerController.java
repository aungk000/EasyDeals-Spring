package com.losersclub.easydeals.api;

import com.losersclub.easydeals.Spring;
import com.losersclub.easydeals.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/customer")
@RestController
public class CustomerController extends Spring.Controller<Customer> {

    @Autowired
    public CustomerController(Spring.Service<Customer> service) {
        super(service);
    }

    @PostMapping
    public void add(@Valid @NotNull @RequestBody Customer customer) {
        super.add(customer);
    }

    @GetMapping
    public List<Customer> getAll() {
        return super.getAll();
    }

    @GetMapping(path = "{id}")
    public Customer get(@PathVariable("id") UUID id) {
        return super.get(id);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") UUID id) {
        super.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Customer customer) {
        super.update(id, customer);
    }
}
