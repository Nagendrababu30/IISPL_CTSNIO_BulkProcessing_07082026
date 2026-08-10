package com.iispl.service;

import java.math.BigDecimal;

import com.iispl.model.TransactionRequest;
import com.iispl.model.TransactionResult;
import com.iispl.nio.NioXmlReader;

public interface TransactionService {
	
	public void processTransaction(TransactionRequest request);

	public TransactionResult validate(TransactionRequest request);
	
	public boolean debitAmount(String accountNumber, BigDecimal amount);
	
	public boolean creditAmount(String accountNumber, BigDecimal amount);
	
	public void saveTransaction(TransactionRequest request, TransactionResult result);
	
}
