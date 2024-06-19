package com.cjc.loanapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
@GetMapping("/getAllCustomerByAccept")
public ResponseEntity<List<Customer>> getAllCustomerByAccept() {
	List<Customer>list = cs.getAllCustomerByAccept();
	if(!list.isEmpty()) {
		log.info("Getting all Sucess status customer sucessfully");
		return new ResponseEntity<List<Customer>>(list,HttpStatus.OK);
	}
	else {
		log.error("Getting all sucess status customer failed");
		return new ResponseEntity<List<Customer>>(HttpStatus.BAD_REQUEST);
	}
	
}

@GetMapping("/getAllCustomerByReject")
public ResponseEntity<List<Customer>>getAllCustomerByReject(){
	List<Customer>list = cs.getAllCustomerByReject();
	if(!list.isEmpty()) {
		log.info("Getting all Sucess status customer sucessfully");
		return new ResponseEntity<List<Customer>>(list,HttpStatus.OK);
	}
	else {
		log.error("Getting all sucess status customer failed");
		return new ResponseEntity<List<Customer>>(HttpStatus.BAD_REQUEST);
	}
}





@GetMapping("/getCustomerById/{customerId}")
public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {
	Customer cust=cs.getCustomerById(customerId);
	if(cust!=null) {
		log.info("Getting customer ID sucessfully");
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}
	else {
		log.error("Failed to getting customer By ID");
		return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
	}
	
}

@PutMapping("disburseLoan/{customerId}")
public ResponseEntity<Customer>disburseLoan(@PathVariable Integer customerId){
	System.out.println(customerId);
	System.out.println("In disburse loan");
	Customer cust = cs.disburseLoan(customerId);
	if(cust!=null) {
		log.info("Loan dusburse sucessfully for customer");
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}
	else {
		log.error("failed to disburse the loan");
		return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
	}
			
}


@GetMapping("getAllDisburseCustomer")
public ResponseEntity<List<Customer>>getAllDisburseCustomer(){
	List<Customer> list = cs.getAllDisburseCustomer();
	if(!list.isEmpty()) {
		return new ResponseEntity<List<Customer>>(list,HttpStatus.OK);
	}
	else {
		return new ResponseEntity<List<Customer>>(HttpStatus.BAD_REQUEST);
	}
}
	
	
}
