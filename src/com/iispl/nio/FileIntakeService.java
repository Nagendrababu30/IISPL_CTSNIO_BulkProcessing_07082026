package com.iispl.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import javax.xml.stream.XMLStreamException;

import com.iispl.exceptions.InvalidFileNameException;
import com.iispl.exceptions.InvalidInputFileException;
import com.iispl.model.FileProcessingSummary;

public class FileIntakeService {

	private Path incomingFolder = Paths.get("data", "incoming");
	private Path processingFolder = Paths.get("data", "processing");
	private Path outputFolder = Paths.get("data", "output");
	private Path archiveFolder = Paths.get("data", "archive");
	private Path rejectedFolder = Paths.get("data", "rejected");
	NioXmlReader nioXmlReader = new NioXmlReader();
	ResponseFileWriter fileWriter = null;
	ArchiveService archiveService = new ArchiveService();

	public void createDataFolders() {

		try {

			Files.createDirectories(incomingFolder);
			Files.createDirectories(processingFolder);
			Files.createDirectories(outputFolder);
			Files.createDirectories(archiveFolder);
			Files.createDirectories(rejectedFolder);
			

		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	public void validate() {

		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(incomingFolder, "*.xml");

			for (Path file : stream) {

				BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);

				try {
					if (isRegularFile(attributes) && isValidFileName(file.getFileName().toString())) {

						Path processingFile = moveFileToProcessing(file);
						fileWriter = new ResponseFileWriter(outputFolder, rejectedFolder, file.getFileName().toString()) ;

						System.out.println("Processing file: " + processingFile);

						FileProcessingSummary fileProcessingSummary = nioXmlReader.readXml(processingFile);
						fileWriter.writeFileSummary(fileProcessingSummary);
					}

				} catch (InvalidInputFileException | InvalidFileNameException exception) {
					exception.printStackTrace();
				} catch (XMLStreamException e) {
					e.printStackTrace();
				}
				
				archiveService.moveFileToArchive(file, archiveFolder, processingFolder);

			}

			stream.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private boolean isValidFileName(String fileName) throws InvalidFileNameException {
		String regex = "^TXN_CORP\\d{3}_\\d{8}_\\d{3}.xml";

		if (fileName.matches(regex)) {
			return true;
		}

		throw new InvalidFileNameException();
	}

	private boolean isRegularFile(BasicFileAttributes attributes) throws InvalidInputFileException {

		if (attributes.isRegularFile()) {
			return true;
		}

		throw new InvalidInputFileException();
	}

	public Path moveFileToProcessing(Path file) {

		Path destination = null;

		try {
			Path source = incomingFolder.resolve(file.getFileName());

			destination = processingFolder.resolve(file.getFileName());

			Files.move(source, destination, StandardCopyOption.ATOMIC_MOVE);

		} catch (IOException exception) {
			exception.printStackTrace();
		}

		return destination;

	}

	public Path getProcessingFolder() {

		return processingFolder;
	}

}
