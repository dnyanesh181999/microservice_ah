package com.cjc.loanapplication.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cjc.loanapplication.model.Customer;
@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer>{

	public List<Customer> findAllByStatus(String string);

}
