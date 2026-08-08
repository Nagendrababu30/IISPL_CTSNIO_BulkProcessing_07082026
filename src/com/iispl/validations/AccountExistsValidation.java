package com.iispl.validations;

import com.iispl.dao.AccountDAO;
import com.iispl.dao.AccountDAOImpl;
import com.iispl.model.Account;

public class AccountExistsValidation implements AccountValidator {
	AccountDAO accountDAO = new AccountDAOImpl();

	@Override
	public boolean validate(Account account) {
		account = accountDAO.getAccountByNumber(account.getAccountNumber());
		if(account != null) {
			return true;
		}
		return false;
	}

}
