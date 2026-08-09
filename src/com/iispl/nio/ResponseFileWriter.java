package com.iispl.nio;

import java.nio.file.Path;

import com.iispl.model.FileProcessingSummary;
import com.iispl.model.TransactionRequest;
import com.iispl.model.TransactionResult;

public class ResponseFileWriter {
	
	Path outputFolder;
	Path rejectedFolder;
	String fileName;
	
	public ResponseFileWriter(Path outputFolder, Path rejectedFolder, String fileName) {
		this.outputFolder = outputFolder;
		this.rejectedFolder = rejectedFolder;
		this.fileName = fileName;
	}

	public void writeSuccessTransaction(TransactionRequest transactionRequest, TransactionResult transactionResult) {
		
	}
	
	public void writeFailedTransaction(TransactionRequest transactionRequest, TransactionResult transactionResult) {
		
	}
	
	public void writeFileSummary(FileProcessingSummary fileProcessingSummary) {
		
	}
	
}
