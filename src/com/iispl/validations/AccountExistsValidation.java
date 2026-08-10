package com.iispl.validations;

import com.iispl.model.Account;

public class AccountExistsValidation implements AccountValidator {

	@Override
	public boolean validate(Account account) {
		return (account != null);
	}

}
