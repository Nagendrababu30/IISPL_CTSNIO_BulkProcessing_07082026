package com.iispl.validations;

import com.iispl.model.TransactionRequest;

public class TransactionTypeValidation implements TransactionValidator {

	@Override
	public boolean validate(TransactionRequest transactionRequest) {
		if( transactionRequest.getTransactionType().equals("FUND_TRANSFER") ||

				 transactionRequest.getTransactionType().equals("SALARY_CREDIT") ||

				 transactionRequest.getTransactionType().equals("VENDOR_PAYMENT") ||

				 transactionRequest.getTransactionType().equals("UTILITY_PAYMENT")) {

					return true ;

				}

				else {

					return false;

				}

				 
	}

}
