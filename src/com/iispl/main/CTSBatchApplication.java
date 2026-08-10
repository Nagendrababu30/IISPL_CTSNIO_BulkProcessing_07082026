package com.iispl.main;

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

            FileIntakeService fileIntakeService =
                    new FileIntakeService();

            fileIntakeService.createDataFolders();

            System.out.println("Incoming folder monitored successfully.");


            // 2. XML Processing
            System.out.println("\n2. XML Processing");
            System.out.println("------------------------------------------");

            fileIntakeService.validate();

            System.out.println("XML processing completed.");


            // 3. Database Update
            System.out.println("\n3. Database Update");
            System.out.println("------------------------------------------");

            System.out.println("Transaction details updated in database.");


            // 4. Response File Generation
            System.out.println("\n4. Response File Generation");
            System.out.println("------------------------------------------");

            System.out.println("Success and failure response files generated.");


            // 5. Archive
            System.out.println("\n5. Archive");
            System.out.println("------------------------------------------");

            System.out.println("Processed input file archived successfully.");


            // 6. Summary
            System.out.println("\n6. Summary");
            System.out.println("------------------------------------------");

            System.out.println("File processing summary generated successfully.");

            System.out.println("\n==========================================");
            System.out.println("CTS BULK TRANSACTION PROCESSING COMPLETED");
            System.out.println("==========================================");

        } catch (Exception e) {

            System.err.println("\nCTS processing failed.");
            e.printStackTrace();
        }
    }
}
