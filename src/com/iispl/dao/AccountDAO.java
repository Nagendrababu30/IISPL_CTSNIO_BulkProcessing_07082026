package com.iispl.dao;

import java.math.BigDecimal;

import com.iispl.model.Account;

public interface AccountDAO {

	public Account getAccountByNumber(String accountNumber);
	
	public boolean updateBalance(String accountNumber, BigDecimal amount);
	
}
