package com.iispl.validations;

import com.iispl.model.TransactionRequest;

public class TransactionIdValidation implements TransactionValidator {

	@Override
	public boolean validate(TransactionRequest transactionRequest) {
		if(transactionRequest.getTransactionId().equals("")) {
			return false;
		}
		else {
			 return true;
		}
	 
	}

}
