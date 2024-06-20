package com.cjc.loanapplication.servicei;

import java.util.List;

import com.cjc.loanapplication.model.Customer;

public interface CustomerServicei {

	public List<Customer> getAllCustomerByAccept();

	public Customer getCustomerById(Integer customerId);

	public Customer disburseLoan(Integer customerId);

	public List<Customer> getAllCustomerByReject();

	public List<Customer> getAllDisburseCustomer();

	public List<Customer> getAllCustomerByLoanPaid();


}
