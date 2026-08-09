package com.iispl.nio;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.iispl.enums.TransactionType;
import com.iispl.model.FileProcessingSummary;
import com.iispl.model.TransactionRequest;

public class NioXmlReader {

	FileProcessingSummary summary;

	public FileProcessingSummary readXml(Path path) throws IOException, XMLStreamException {

		summary = new FileProcessingSummary(null, path.getFileName(), 0, 0, 0);

		XMLInputFactory factory = XMLInputFactory.newInstance();

		try (InputStream inputStream = Files.newInputStream(path)) {

			XMLStreamReader reader = factory.createXMLStreamReader(inputStream);

			TransactionRequest transaction = null;
			String batchId=null;

			while (reader.hasNext()) {

				int event = reader.next();

				// START ELEMENT
				if (event == XMLStreamConstants.START_ELEMENT) {

					String element = reader.getLocalName();
					

					// <bulkTransactions batchId="" corporateId="" createdDate="">
					if ("bulkTransactions".equals(element)) {

						batchId = reader.getAttributeValue(null, "batchId");

						summary.setBatchId(batchId);
					}

					// <transaction>
					else if ("transaction".equals(element)) {

						transaction = new TransactionRequest(null, batchId, null, null, null, null);
					}

					// Transaction fields
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

				// END ELEMENT
				else if (event == XMLStreamConstants.END_ELEMENT) {

					// </transaction>
					if ("transaction".equals(reader.getLocalName())) {

						System.out.println(transaction);

						// Send one transaction to service
//	                    transactionService.processTransaction(transaction);

						transaction = null;
					}
				}
			}

			reader.close();
		}

		return summary;
	}

}
