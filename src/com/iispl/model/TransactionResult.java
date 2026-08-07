package com.iispl.model;

import java.nio.file.Path;
import java.time.LocalDate;

import com.iispl.enums.TransactionStatus;

public class TransactionResult {

	private String transactionId;
	private Path sourceFile;
	private String remarks;
	private TransactionStatus status;
	private LocalDate processingDate;

	public TransactionResult(String transactionId, Path sourceFile, String remarks, TransactionStatus status,
			LocalDate processingDate) {
		this.transactionId = transactionId;
		this.sourceFile = sourceFile;
		this.remarks = remarks;
		this.status = status;
		this.processingDate = processingDate;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Path getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(Path sourceFile) {
		this.sourceFile = sourceFile;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public LocalDate getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(LocalDate processingDate) {
		this.processingDate = processingDate;
	}

}
