package com.iispl.validations;

import com.iispl.enums.AccountStatus;
import com.iispl.model.Account;

public class AccountStatusValidation implements AccountValidator {

	@Override
	public boolean validate(Account account) {
		// TODO Auto-generated method stub
		if (account.getStatus() == AccountStatus.ACTIVE) {
	        return true;
	    } else {
	        return false;
	    }
	}

}
