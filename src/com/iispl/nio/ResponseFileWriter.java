package com.iispl.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

import com.iispl.dao.FileProcessingDAO;
import com.iispl.dao.FileProcessingDAOImpl;
import com.iispl.model.FileProcessingSummary;
import com.iispl.model.TransactionRequest;
import com.iispl.model.TransactionResult;
import com.iispl.service.TransactionService;
import com.iispl.service.TransactionServiceImpl;

public class ResponseFileWriter {

	Path outputFolder;
	Path rejectedFolder;
	String fileName;
	TransactionService transactionService = new TransactionServiceImpl();
	FileProcessingDAO fileProcessingDAO = new FileProcessingDAOImpl();

	public ResponseFileWriter(Path outputFolder, Path rejectedFolder, String fileName) {
		this.outputFolder = outputFolder;
		this.rejectedFolder = rejectedFolder;
		this.fileName = fileName;
		
		try {
            Files.createDirectories(outputFolder);
            Files.createDirectories(rejectedFolder);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to create response directories", e);
        }
	}
	
	public ResponseFileWriter() {
		
	}

	public void writeSuccessTransaction(TransactionRequest transactionRequest, TransactionResult transactionResult) {
		try {

			String responseFileName = "RESP_Success_" + fileName;

			Path responseFile = outputFolder.resolve(responseFileName);

			transactionResult.setSourceFile(responseFile);

			StringBuilder xml = new StringBuilder();
			
//			xml.append("<?xml version=\"1.0\"encoding=\"UTF-8\"?>\n");

			xml.append("\n<Transaction>\n");

			xml.append("<TransactionRequest>\n");

			xml.append("<transactionId>").append(transactionRequest.getTransactionId())

					.append("</transactionId>\n");

			xml.append("        <batchId>").append(transactionRequest.getBatchId())

					.append("</batchId>\n");

			xml.append("        <fromAccount>").append(transactionRequest.getFromAccount())

					.append("</fromAccount>\n");

			xml.append("        <toAccount>").append(transactionRequest.getToAccount())

					.append("</toAccount>\n");

			xml.append("<transactionAmount>").append(transactionRequest.getTransactionAmount())

					.append("</transactionAmount>\n");

			xml.append("<transactionType>").append(transactionRequest.getTransactionType())

					.append("</transactionType>\n");

			xml.append("</TransactionRequest>\n");

			xml.append("<TransactionResult>\n");

			xml.append("         <sourceFile>").append(transactionResult.getSourceFile())

					.append("</sourceFile>\n");

			xml.append("         <remarks>").append(transactionResult.getRemarks())

					.append("</remarks>\n");

			xml.append("         <status>").append(transactionResult.getStatus())

					.append("</status>\n");

			xml.append("<processingDate>").append(transactionResult.getProcessingDate())

					.append("</processingDate>\n");

			xml.append("</TransactionResult>\n");

			xml.append("</Transaction>");

			Files.writeString(responseFile, xml.toString(),
					StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING,
					StandardOpenOption.WRITE);
			
			transactionService.saveTransaction(transactionRequest, transactionResult);

		}

		catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}

	
	public void writeFailedTransaction(TransactionRequest transactionRequest, TransactionResult transactionResult) throws IOException {
		
		String responseFileName = "RESP_Failed_" + fileName;
		Path rejectedFile = rejectedFolder.resolve(responseFileName);
		
		
		transactionResult.setSourceFile(rejectedFile);

		StringBuilder xml = new StringBuilder();
		
//		xml.append("<?xml version=\"1.0\"encoding=\"UTF-8\"?>\n");

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

		Files.writeString(rejectedFile, xml.toString(), StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING,
				StandardOpenOption.WRITE);
		transactionService.saveTransaction(transactionRequest, transactionResult);
	}

	public void writeFileSummary(FileProcessingSummary fileProcessingSummary) {
		try {
			   Path summaryFile = outputFolder.resolve("SUMMARY_" + fileName);
			   
			   fileProcessingSummary.setFileName(summaryFile);
			   
			   StringBuilder sb = new StringBuilder();
			   sb.append("<FileProcessingSummary>\n");
			   sb.append("<batchId>").append(fileProcessingSummary.getBatchId()).append("</batchId>\n");
			   sb.append("<fileName>").append(fileProcessingSummary.getFileName()).append("</fileName>\n");
			   sb.append("<totalRecords>").append(fileProcessingSummary.getTotalRecords()).append("</totalRecords>\n");
			   sb.append("<failureRecords>").append(fileProcessingSummary.getFailureRecords()).append("</failureRecords>\n");
			   sb.append("<successfulRecords>").append(fileProcessingSummary.getSuccessfullRecords()).append("</successfulRecords>\n");
			   sb.append("</FileProcessingSummary>");
			   
			   Files.writeString(summaryFile, sb.toString(), StandardOpenOption.CREATE,
					   StandardOpenOption.TRUNCATE_EXISTING,
					   StandardOpenOption.WRITE);
			   fileProcessingDAO.saveFileProcessingSummary(fileProcessingSummary);
			   
			  }catch (IOException e) {
			   e.printStackTrace();
			   
			  }
	}

}
