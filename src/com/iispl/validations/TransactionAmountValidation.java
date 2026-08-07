package com.iispl.validations;

import java.math.BigDecimal;

import com.iispl.model.TransactionRequest;

public class TransactionAmountValidation implements TransactionValidator {

	@Override
	public boolean validate(TransactionRequest transactionRequest) {
		// TODO Auto-generated method stub
		if(transactionRequest.getTransactionAmount().compareTo(BigDecimal.ZERO)<0) {
			return false;
		}else {
			return true;
		}
	}

}
