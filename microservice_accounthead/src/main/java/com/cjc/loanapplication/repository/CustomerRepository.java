package com.cjc.loanapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cjc.loanapplication.model.Customer;
@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer>{

}
