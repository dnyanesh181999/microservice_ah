package com.cjc.loanapplication.utility;

public class GetLoanAmountByIntrest {
	 public static Double calculateTotalWithSimpleInterest(Double principal, Float rate, Integer tenure) {
	        return principal * (1 + (rate * tenure));
	 }
}
