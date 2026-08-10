package com.iispl.validations;

import com.iispl.dao.AccountDAO;
import com.iispl.model.Account;
import com.iispl.model.TransactionRequest;

public class  SufficientFundValidator implements TransactionValidator {
     AccountDAO accountDao=null;
     
     public SufficientFundValidator(AccountDAO accountDAO) {
    	 this.accountDao = accountDAO;
     }
     
	@Override
	public boolean validate(TransactionRequest transactionRequest) {
	Account account=	accountDao.getAccountByNumber( transactionRequest.getFromAccount());
	 if(account.getAvailableBalance().compareTo(transactionRequest.getTransactionAmount())>0) {
		 return true ;
	 }
	 else {
		 return false;
	 }
		 
		 
	}

}
