package com.iispl.model;

import java.math.BigDecimal;

import com.iispl.enums.TransactionType;

public class TransactionRequest {

	private String transactionId;
	private String batchId;
	private String fromAccount;
	private String toAccount;
	private BigDecimal transactionAmount;
	private TransactionType transactionType;

	public TransactionRequest(String transactionId, String batchId, String fromAccount, String toAccount,
			BigDecimal transactionAmount, TransactionType transactionType) {
		this.transactionId = transactionId;
		this.batchId = batchId;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "TransactionRequest [transactionId=" + transactionId + ", batchId=" + batchId + ", fromAccount="
				+ fromAccount + ", toAccount=" + toAccount + ", transactionAmount=" + transactionAmount
				+ ", transactionType=" + transactionType + "]";
	}
	
	

}
