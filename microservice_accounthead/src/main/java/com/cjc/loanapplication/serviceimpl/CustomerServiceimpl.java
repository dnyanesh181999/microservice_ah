package com.cjc.loanapplication.serviceimpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.loanapplication.exceptions.ResourseNotFoundException;
import com.cjc.loanapplication.model.Customer;
import com.cjc.loanapplication.model.EmiStatement;
import com.cjc.loanapplication.model.Ledger;
import com.cjc.loanapplication.model.LoanDisbursement;
import com.cjc.loanapplication.repository.CustomerRepository;
import com.cjc.loanapplication.servicei.CustomerServicei;
import com.cjc.loanapplication.utility.GetLoanAmountByIntrest;

@Service
public class CustomerServiceimpl implements CustomerServicei{
@Autowired
CustomerRepository cr;

@Override
public List<Customer> getAllCustomerByAccept() {
	
	List<Customer>list=cr.findAllByStatus("Accept");
	if(!list.isEmpty()) {
		return list;
	}
	else {
		throw new ResourseNotFoundException("No any customer found with status Accept");
	}
}

@Override
public Customer getCustomerById(Integer customerId) {
	Optional<Customer>opt=cr.findById(customerId);
	if(opt.isPresent()) {
		return opt.get();
	}
	else {
		throw new ResourseNotFoundException("No Any Customer Found with ID");
	}
	
}

@Override
public Customer disburseLoan(Integer customerId) {
	Optional<Customer>opt=cr.findById(customerId);
	if(opt.isPresent()) {
		Customer c=opt.get();
		c.setStatus("Loan Disburse");
		LoanDisbursement l=c.getLoanDisbursement();
		l.setAccountNumber(c.getCustomerAccountDetails().getAccountNo());
		l.setAccountType(c.getCustomerAccountDetails().getAccountType());
		LocalDate localDate = LocalDate.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	     String formattedDate = localDate.format(formatter);
	     l.setAggrementDate(formattedDate);
	     l.setAmountPaidDate(formattedDate);
	     l.setAmountPayType("Net Banking");
	     l.setBankName(c.getCustomerAccountDetails().getBankName());
	     l.setIfscCode(c.getCustomerAccountDetails().getIfscCode());
	     l.setLoanNumber(c.getCustomerId());
	     l.setPaymentStatus("Disburse");
	     l.setTotalAmount(c.getSanctionLetter().getLoanAmountSanctioned());
	     l.setTransferAmount(c.getSanctionLetter().getLoanAmountSanctioned()-c.getSanctionLetter().getMonthlyEmiAmount());
	     c.setLoanDisbursement(l);
	     
	     Ledger lg=c.getLedger();
	     lg.setAmountPaidTillDate(c.getSanctionLetter().getMonthlyEmiAmount());
	     lg.setDefaulterCount(0);
	     lg.setLedgerCreateDate(formattedDate);
	     
	     
	     LocalDate currentDate = LocalDate.now();
	     LocalDate futureDate = currentDate.plusYears(c.getSanctionLetter().getLoanTenure());
	     lg.setLoanEndDate(""+futureDate);
	     lg.setLoanStatus("Unpaid");
	     lg.setMonthlyEmi(c.getSanctionLetter().getMonthlyEmiAmount());
	     lg.setPayableAmountWithInterest(GetLoanAmountByIntrest.calculateTotalWithSimpleInterest(c.getSanctionLetter().getLoanAmountSanctioned(), c.getSanctionLetter().getRateOfInterest(), c.getSanctionLetter().getLoanTenure()));
	     lg.setRemainingAmount(c.getSanctionLetter().getLoanAmountSanctioned()-c.getSanctionLetter().getMonthlyEmiAmount());
	     lg.setTenure(c.getSanctionLetter().getLoanTenure());
	     lg.setTotalLoanAmount(c.getSanctionLetter().getLoanAmountSanctioned());
	     List<EmiStatement>li=lg.getEmiStatement();
	     EmiStatement emi = new EmiStatement();
	     emi.setAmount(c.getSanctionLetter().getMonthlyEmiAmount());
	     emi.setDate(formattedDate);
	     emi.setStatus("PAID");
	     li.add(emi);
	     lg.setEmiStatement(li);
	     c.setLedger(lg);
	     
	     Customer customer=cr.save(c);
	     if(customer!=null){
	    	 return customer;
	     }
	     else {
	    	 throw new ResourseNotFoundException("Failed To Process for Disbursement");
	     }
	     
		
	}
	else {
		throw new ResourseNotFoundException("No Any customer Found For disburse Loan process");
	}
}

@Override
public List<Customer> getAllCustomerByReject() {
	List<Customer>list=cr.findAllByStatus("Reject");
	if(!list.isEmpty()) {
		return list;
	}
	else {
		throw new ResourseNotFoundException("No any customer found with status Accept");
	}
	
}

@Override
public List<Customer> getAllDisburseCustomer() {
	List<Customer>list=cr.findAllByStatus("Loan Disburse");
	if(!list.isEmpty()) {
		return list;
	}
	else {
		throw new ResourseNotFoundException("No any customer found with status Accept");
	}
}

@Override
public List<Customer> getAllCustomerByLoanPaid() {
	List<Customer>list=cr.findAllByStatus("LoanPaid");
	if(!list.isEmpty()) {
		return list;
	}
	else {
		throw new ResourseNotFoundException("No any customer found with status Accept");
	}
}


}
