package com.losersclub.easydeals.service;

import com.losersclub.easydeals.Spring;
import com.losersclub.easydeals.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends Spring.Service<Customer> {

    @Autowired
    public CustomerService(@Qualifier("postgres") Spring.Dao<Customer> dao) {
        super(dao);
    }
}
