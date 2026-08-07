package com.iispl.model;

import java.math.BigDecimal;

import com.iispl.enums.AccountStatus;

public class Account {

	private String accountNumber;
	private AccountStatus status;
	private BigDecimal availableBalance;
	private String accountHolderName;

	public Account(String accountNumber, AccountStatus status, BigDecimal availableBalance, String accountHolderName) {
		super();
		this.accountNumber = accountNumber;
		this.status = status;
		this.availableBalance = availableBalance;
		this.accountHolderName = accountHolderName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", status=" + status + ", availableBalance="
				+ availableBalance + ", accountHolderName=" + accountHolderName + "]";
	}

}
