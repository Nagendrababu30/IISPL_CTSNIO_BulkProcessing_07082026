package com.iispl.validations;

import com.iispl.model.TransactionRequest;

public class DifferentAccountsValidation implements TransactionValidator {

	@Override
	public boolean validate(TransactionRequest transactionRequest) {
		if(transactionRequest.getToAccount() == null || transactionRequest.getFromAccount() == null)
			return false;
			
		if(transactionRequest.getFromAccount().equals(transactionRequest.getToAccount()))
			return false;
		
		return true;
	}

}
