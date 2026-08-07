package com.iispl.main;

import com.iispl.nio.NioXmlReader;
import com.iispl.service.TransactionService;
import com.iispl.service.TransactionServiceImpl;

public class CTSBatchApplication {

	TransactionService transactionService = new TransactionServiceImpl();
	NioXmlReader nioXmlReader = new NioXmlReader();
	
	public static void main(String[] args) {
		
		

	}

}
