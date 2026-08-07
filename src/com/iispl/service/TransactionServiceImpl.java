package com.iispl.service;

import java.math.BigDecimal;

import com.iispl.dao.AccountDAO;
import com.iispl.dao.AccountDAOImpl;
import com.iispl.dao.FileProcessingDAO;
import com.iispl.dao.FileProcessingDAOImpl;
import com.iispl.dao.TransactionDAO;
import com.iispl.dao.TransactionDAOImpl;
import com.iispl.model.TransactionRequest;
import com.iispl.model.TransactionResult;
import com.iispl.nio.NioXmlReader;

public class TransactionServiceImpl implements TransactionService {
	
	AccountDAO accountDAO = new AccountDAOImpl();
	TransactionDAO transactionDAO = new TransactionDAOImpl();
	FileProcessingDAO fIleProcessingDAO = new FileProcessingDAOImpl();

	@Override
	public void processTransactionFile(NioXmlReader nioXmlReader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TransactionResult validate(TransactionRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean debitAmount(String accountNumber, BigDecimal amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean creditAmount(String accountNumber, BigDecimal amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
