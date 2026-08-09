package com.iispl.dao;

import com.iispl.model.TransactionRequest;
import com.iispl.model.TransactionResult;

public interface TransactionDAO {
	
	public boolean saveTransaction(TransactionRequest request, TransactionResult result);
	
}
