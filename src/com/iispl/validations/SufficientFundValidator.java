package com.iispl.validations;

import com.iispl.dao.AccountDAO;
import com.iispl.dao.AccountDAOImpl;
import com.iispl.model.Account;
import com.iispl.model.TransactionRequest;

public class  SufficientFundValidator implements TransactionValidator {
     AccountDAO accountDao=new AccountDAOImpl();
	@Override
	
	public boolean validate(TransactionRequest transactionRequest) {
	Account account=	accountDao.getAccountByNumber( transactionRequest.getTransactionId());
	 if(account.getAvailableBalance().compareTo(transactionRequest.getTransactionAmount())<0) {
		 return false ;
	 }
	 else {
		 return false;
	 }
		 
		 
	}

}
