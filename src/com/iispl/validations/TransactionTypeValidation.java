package com.iispl.validations;

import com.iispl.enums.TransactionType;
import com.iispl.model.TransactionRequest;

public class TransactionTypeValidation implements TransactionValidator {

	@Override
	public boolean validate(TransactionRequest transactionRequest) {
		if( transactionRequest.getTransactionType() == TransactionType.FUND_TRANSFER ||

				 transactionRequest.getTransactionType() == TransactionType.SALARY_CREDIT ||

				 transactionRequest.getTransactionType() == TransactionType.VENDOR_PAYMENT ||

				 transactionRequest.getTransactionType() == TransactionType.UTILITY_PAYMENT) {

					return true ;

				}

				else {

					return false;

				}

				 
	}

}
