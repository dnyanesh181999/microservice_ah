package com.cjc.loanapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.loanapplication.model.Customer;
import com.cjc.loanapplication.servicei.CustomerServicei;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@CrossOrigin("*")
@RequestMapping("/customer")
@RestController
public class CustomerController {
@Autowired
CustomerServicei cs;
	
	public ResponseEntity<Customer>loginCheck(@PathVariable String username,@PathVariable String password){
	
		Customer cust = cs.loginCheck(username,password);
		if(cust!=null) {
			return new ResponseEntity<Customer>(cust,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
