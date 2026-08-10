package com.iispl.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.iispl.dao.AccountDAO;
import com.iispl.dao.AccountDAOImpl;
import com.iispl.dao.FileProcessingDAO;
import com.iispl.dao.FileProcessingDAOImpl;
import com.iispl.dao.TransactionDAO;
import com.iispl.dao.TransactionDAOImpl;
import com.iispl.enums.TransactionStatus;
import com.iispl.model.Account;
import com.iispl.model.TransactionRequest;
import com.iispl.model.TransactionResult;
import com.iispl.nio.NioXmlReader;
import com.iispl.validations.AccountExistsValidation;
import com.iispl.validations.AccountStatusValidation;
import com.iispl.validations.AccountValidator;
import com.iispl.validations.DifferentAccountsValidation;
import com.iispl.validations.SufficientFundValidator;
import com.iispl.validations.TransactionAmountValidation;
import com.iispl.validations.TransactionIdValidation;
import com.iispl.validations.TransactionTypeValidation;
import com.iispl.validations.TransactionValidator;

public class TransactionServiceImpl implements TransactionService {
	
	AccountDAO accountDAO = new AccountDAOImpl();
	TransactionDAO transactionDAO = new TransactionDAOImpl();
	FileProcessingDAO fIleProcessingDAO = new FileProcessingDAOImpl();
	List<AccountValidator> accountValidationRules = null;
	List<TransactionValidator> transactionValidationRules = null;
	
	public TransactionServiceImpl() {
		accountValidationRules = new ArrayList<AccountValidator>();
		transactionValidationRules = new ArrayList<TransactionValidator>();
		accountValidationRules.add(new AccountExistsValidation());
		accountValidationRules.add(new AccountStatusValidation());
		transactionValidationRules.add(new DifferentAccountsValidation());
		transactionValidationRules.add(new SufficientFundValidator(accountDAO));
		transactionValidationRules.add(new TransactionAmountValidation());
		transactionValidationRules.add(new TransactionIdValidation());
		transactionValidationRules.add(new TransactionTypeValidation());
	}

	@Override
	public void processTransaction(TransactionRequest request) {
		TransactionResult transactionResult = validate(request);
		
	}

	@Override
	public TransactionResult validate(TransactionRequest request) {
		TransactionResult transactionResult = new TransactionResult(request.getTransactionId(),
				null, null, null, LocalDate.now());
		
		int fromAccount = validateAccount(request.getFromAccount());
		
		if(fromAccount == 1) {
			
			transactionResult.setRemarks("From Account doesnot exists.");
			transactionResult.setStatus(TransactionStatus.FAILED);
			
			return transactionResult;
			
		} else if(fromAccount == 2) {
			transactionResult.setRemarks("From Account should be active.");
			transactionResult.setStatus(TransactionStatus.FAILED);
			
			return transactionResult;
		}
		
		int toAccount = validateAccount(request.getToAccount());
		
		if(toAccount == 1) {
			
			transactionResult.setRemarks("From Account doesnot exists.");
			transactionResult.setStatus(TransactionStatus.FAILED);
			
			return transactionResult;
			
		} else if(fromAccount == 2) {
			transactionResult.setRemarks("From Account should be active.");
			transactionResult.setStatus(TransactionStatus.FAILED);
			
			return transactionResult;
		}
		
		boolean isValidTransaction = true;
		
		for(TransactionValidator rule : transactionValidationRules) {
			if(!rule.validate(request)) {
				isValidTransaction = false;
				
				if(rule instanceof DifferentAccountsValidation) {
					transactionResult.setRemarks("From account and to account are same");
					transactionResult.setStatus(TransactionStatus.FAILED);
				} else if(rule instanceof SufficientFundValidator) {
					transactionResult.setRemarks("Insufficient balance.");
					transactionResult.setStatus(TransactionStatus.FAILED);
				} else if(rule instanceof TransactionAmountValidation) {
					transactionResult.setRemarks("Amount not valid.");
					transactionResult.setStatus(TransactionStatus.FAILED);
				} else if(rule instanceof TransactionIdValidation) {
					transactionResult.setRemarks("Invalid transaction id");
					transactionResult.setStatus(TransactionStatus.FAILED);
				} else if(rule instanceof TransactionTypeValidation) {
					transactionResult.setRemarks("Invalid transaction type.");
					transactionResult.setStatus(TransactionStatus.FAILED);
				}
				
			}
		}
		
		if(isValidTransaction) {
			
			debitAmount(request.getFromAccount(), request.getTransactionAmount());
			creditAmount(request.getToAccount(), request.getTransactionAmount());
			
			transactionResult.setRemarks("Sucessfull transaction.");
			transactionResult.setStatus(TransactionStatus.SUCCESS);
		}
		
		return transactionResult;
	}

	@Override
	public boolean debitAmount(String accountNumber, BigDecimal amount) {
	Account account= accountDAO.getAccountByNumber(accountNumber);
	BigDecimal updatedBalance= account.getAvailableBalance().subtract(amount);
		
	return	accountDAO.updateBalance(accountNumber, updatedBalance);
		 
	}

	@Override
	public boolean creditAmount(String accountNumber, BigDecimal amount) {
		Account account = accountDAO.getAccountByNumber(accountNumber);
		BigDecimal newAmount = account.getAvailableBalance().add(amount);
		account.setAvailableBalance(newAmount);
		accountDAO.updateBalance(accountNumber, newAmount);
		return true;
	}
	
	private int validateAccount(String accountNumber) {
		
		for(AccountValidator rule : accountValidationRules) {
			Account account = accountDAO.getAccountByNumber(accountNumber); 
			
			if(!rule.validate(account)) {
				
				
				if(rule instanceof AccountExistsValidation) {
					return 1;
				} else {
					return 2;
				}
			}
		}
		
		return 0;
	}

	@Override
	public void saveTransaction(TransactionRequest request, TransactionResult result) {
		// TODO Auto-generated method stub
		transactionDAO.saveTransaction(request, result);
	}

}
