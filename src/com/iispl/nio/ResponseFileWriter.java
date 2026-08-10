package com.iispl.nio;

import java.io.IOException;
import java.nio.file.Files;
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
	
	public void writeFailedTransaction(TransactionRequest transactionRequest, TransactionResult transactionResult) throws IOException {
	    Files.createDirectories(rejectedFolder);
		Path rejectedFile = rejectedFolder.resolve(fileName);
		transactionResult.setSourceFile(rejectedFile);

		StringBuilder xml = new StringBuilder();

		xml.append("<Transaction>\n");

		xml.append("<TransactionRequest>\n");

		xml.append("<transactionId>").append(transactionRequest.getTransactionId()).append("</transactionId>\n");

		xml.append("<batchId>").append(transactionRequest.getBatchId()).append("</batchId>\n");

		xml.append(" <fromAccount>").append(transactionRequest.getFromAccount()).append("</fromAccount>\n");

		xml.append(" <toAccount>").append(transactionRequest.getToAccount()).append("</toAccount>\n");

		xml.append("<transactionAmount>").append(transactionRequest.getTransactionAmount())
				.append("</transactionAmount>\n");

		xml.append("<transactionType>").append(transactionRequest.getTransactionType()).append("</transactionType>\n");

		xml.append("</TransactionRequest>\n");

		xml.append("<TransactionResult>\n");

		xml.append("<transactionId>").append(transactionResult.getTransactionId()).append("</transactionId>\n");

		xml.append("<sourceFile>").append(transactionResult.getSourceFile()).append("</sourceFile>\n");

		xml.append("<remarks>").append(transactionResult.getRemarks()).append("</remarks>\n");

		xml.append("<status>").append(transactionResult.getStatus()).append("</status>\n");

		xml.append(" <processingDate>").append(transactionResult.getProcessingDate()).append("</processingDate>\n");

		xml.append(" </TransactionResult>\n");

		xml.append("</Transaction>");

		Files.writeString(rejectedFile, xml.toString());
	}
	
	public void writeFileSummary(FileProcessingSummary fileProcessingSummary) {
		try {
			   Path summaryFile = outputFolder.resolve("SUMMARY_" + fileName +".xml");
			   
			   fileProcessingSummary.setFileName(summaryFile);
			   
			   StringBuilder sb = new StringBuilder();
			   sb.append("<FileProcessingSummary>\n");
			   sb.append("<\t<batchId>").append(fileProcessingSummary.getBatchId()).append("</batchId>\n");
			   sb.append("<fileName>").append(fileProcessingSummary.getFileName()).append("</fileName>\n");
			   sb.append("<totalRecords>").append(fileProcessingSummary.getTotalRecords()).append("</totalRecords>\n");
			   sb.append("<failureRecords>").append(fileProcessingSummary.getFailureRecords()).append("</failureRecords>\n");
			   sb.append("<successfulRecords>").append(fileProcessingSummary.getSuccessfullRecords()).append("</successRecords>\n");
			   sb.append("</FileProcessingSummary>");
			   
			   Files.writeString(summaryFile, sb.toString());
			  }catch (IOException e) {
			   e.printStackTrace();
			   
			  }
	}
	
}
