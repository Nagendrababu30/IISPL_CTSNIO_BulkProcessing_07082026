package com.iispl.validations;

import com.iispl.model.TransactionRequest;

public interface TransactionValidator {

	public boolean validate(TransactionRequest transactionRequest);
	
}
