package com.cjc.loanapplication.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.loanapplication.model.Customer;
import com.cjc.loanapplication.repository.CustomerRepository;
import com.cjc.loanapplication.servicei.CustomerServicei;

@Service
public class CustomerServiceimpl implements CustomerServicei{
@Autowired
CustomerRepository cr;
	@Override
	public Customer loginCheck(String username, String password) {
		
		return null;
	}

}
