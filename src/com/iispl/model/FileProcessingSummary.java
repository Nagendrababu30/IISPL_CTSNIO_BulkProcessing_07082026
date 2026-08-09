package com.iispl.model;

import java.nio.file.Path;

public class FileProcessingSummary {

	private String batchId;
	private Path fileName;
	private long totalRecords;
	private long failureRecords;
	private long successfullRecords;

	public FileProcessingSummary(String batchId, Path fileName, long totalRecords, long failureRecords,
			long successfullRecords) {
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

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public long getFailureRecords() {
		return failureRecords;
	}

	public void setFailureRecords(long failureRecords) {
		this.failureRecords = failureRecords;
	}

	public long getSuccessfullRecords() {
		return successfullRecords;
	}

	public void setSuccessfullRecords(long successfullRecords) {
		this.successfullRecords = successfullRecords;
	}

	@Override
	public String toString() {
		return "FileProcessingSummary [batchId=" + batchId + ", fileName=" + fileName + ", totalRecords=" + totalRecords
				+ ", failureRecords=" + failureRecords + ", successfullRecords=" + successfullRecords + "]";
	}
	
	

}
