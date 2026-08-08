package com.iispl.main;

import com.iispl.nio.FileIntakeService;
import com.iispl.nio.NioXmlReader;
import com.iispl.service.TransactionService;
import com.iispl.service.TransactionServiceImpl;

public class CTSBatchApplication {

	TransactionService transactionService = new TransactionServiceImpl();
	static FileIntakeService fileIntakeService = new FileIntakeService();
	NioXmlReader nioXmlReader = new NioXmlReader();
	
	public static void main(String[] args) {
		
		fileIntakeService.createDataFolders();

	}

}
