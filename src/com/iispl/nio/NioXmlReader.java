package com.iispl.nio;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.iispl.enums.TransactionStatus;
import com.iispl.enums.TransactionType;
import com.iispl.model.FileProcessingSummary;
import com.iispl.model.TransactionRequest;
import com.iispl.model.TransactionResult;
import com.iispl.service.TransactionService;
import com.iispl.service.TransactionServiceImpl;

public class NioXmlReader {

	static FileProcessingSummary summary;

	TransactionService txnService = new TransactionServiceImpl();

	public FileProcessingSummary parseXml(Path path) throws IOException, XMLStreamException {

		long totalTransactions = 0;
		long successTransactions = 0;
		long failedTransactions = 0;

		summary = new FileProcessingSummary(null, path.getFileName(), 0, 0, 0);
		

        String xml =read(path);


		XMLInputFactory factory = XMLInputFactory.newInstance();

		XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));

		TransactionRequest transaction = null;
		String batchId = null;

		while (reader.hasNext()) {

			int event = reader.next();

			//start element
			if (event == XMLStreamConstants.START_ELEMENT) {

				String element = reader.getLocalName();

				// <bulkTransactions batchId="" corporateId="" createdDate="">
				if ("bulkTransactions".equals(element)) {

					batchId = reader.getAttributeValue(null, "batchId");

					summary.setBatchId(batchId);
				}

				// <transaction> tag starting
				else if ("transaction".equals(element)) {

					transaction = new TransactionRequest(null, batchId, null, null, null, null);
				}

				// Setting Transaction fields 
				else if (transaction != null) {

					switch (element) {

					case "transactionId":
						transaction.setTransactionId(reader.getElementText());
						break;

					case "fromAccount":
						transaction.setFromAccount(reader.getElementText());
						break;

					case "toAccount":
						transaction.setToAccount(reader.getElementText());
						break;

					case "type":
						String type = reader.getElementText().trim();

						transaction.setTransactionType(TransactionType.valueOf(type));
						break;

					case "amount":
						transaction.setTransactionAmount(new BigDecimal(reader.getElementText()));
						break;
					}
				}
			}

			// end tags 
			else if (event == XMLStreamConstants.END_ELEMENT) {
				totalTransactions++;

				// </transaction> if transaction end tag counting success failure txns
				if ("transaction".equals(reader.getLocalName())) {

					TransactionResult txnResult = txnService.validate(transaction);
					System.out.println(transaction);
						if(txnResult.getStatus().equals(TransactionStatus.SUCCESS)) {
							successTransactions++;
						}else {
							failedTransactions++;
						}

					transaction = null;
				}
			}
		}
		summary.setTotalRecords(totalTransactions);
		summary.setSuccessfullRecords(successTransactions);
		summary.setFailureRecords(failedTransactions);


		reader.close();
		System.out.println(summary);

		return summary;
	}
	
	
	private String read(Path filePath) throws IOException {

        try (FileChannel channel =
                     FileChannel.open(
                             filePath,
                             StandardOpenOption.READ)) {

            // ByteBuffer capacity is based on file size
            if (channel.size() > Integer.MAX_VALUE) {
                throw new IOException("File is too large");
            }

            ByteBuffer buffer =
                    ByteBuffer.allocate((int) channel.size());

            // Read complete file into ByteBuffer
            while (buffer.hasRemaining()) {
                channel.read(buffer);
            }

            // Switch buffer from write mode to read mode
            buffer.flip();

            // Convert UTF-8 bytes into String
            return StandardCharsets.UTF_8
                    .decode(buffer)
                    .toString();
        }
    }

}
//
