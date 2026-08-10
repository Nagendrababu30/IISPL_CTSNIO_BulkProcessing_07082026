package com.iispl.main;

import com.iispl.model.FileProcessingSummary;
import com.iispl.nio.FileIntakeService;

public class CTSBatchApplication {

	public static void main(String[] args) {

		System.out.println("==========================================");
		System.out.println("   CTS BULK TRANSACTION PROCESSING SYSTEM");
		System.out.println("==========================================");

		try {

			// 1. Monitor Incoming Folder
			System.out.println("\n1. Monitor Incoming Folder");
			System.out.println("------------------------------------------");

			FileIntakeService fileIntakeService = new FileIntakeService();

			fileIntakeService.createDataFolders();

			System.out.println("Incoming folder monitored successfully.");

			// 2. XML Processing
			System.out.println("\n2. XML Processing");
			System.out.println("------------------------------------------");

			FileProcessingSummary summary = fileIntakeService.validate();

			System.out.println();
			System.out.println("Summary");
			System.out.println("------------------------------------------");
		
			System.out.println("Batch ID : " + summary.getBatchId());
			System.out.println("File Name : " + summary.getFileName());
			System.out.println("Total Records : " + summary.getTotalRecords());
			System.out.println("Successful Records: " + summary.getSuccessfullRecords());
			System.out.println("Failed Records : " + summary.getFailureRecords());
			

			System.out.println("XML processing completed.");

			System.out.println("\n==========================================");
			System.out.println("CTS BULK TRANSACTION PROCESSING COMPLETED");
			System.out.println("==========================================");

		} catch (Exception e) {

			System.err.println("\nCTS processing failed.");
			e.printStackTrace();
		}
	}
}
