package com.iispl.model;

import java.nio.file.Path;

public class FileProcessingSummary {

	private String batchId;
	private Path fileName;
	private int totalRecords;
	private int failureRecords;
	private int successfullRecords;

	public FileProcessingSummary(String batchId, Path fileName, int totalRecords, int failureRecords,
			int successfullRecords) {
		super();
		this.batchId = batchId;
		this.fileName = fileName;
		this.totalRecords = totalRecords;
		this.failureRecords = failureRecords;
		this.successfullRecords = successfullRecords;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Path getFileName() {
		return fileName;
	}

	public void setFileName(Path fileName) {
		this.fileName = fileName;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getFailureRecords() {
		return failureRecords;
	}

	public void setFailureRecords(int failureRecords) {
		this.failureRecords = failureRecords;
	}

	public int getSuccessfullRecords() {
		return successfullRecords;
	}

	public void setSuccessfullRecords(int successfullRecords) {
		this.successfullRecords = successfullRecords;
	}

}
