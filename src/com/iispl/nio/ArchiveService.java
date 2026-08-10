package com.iispl.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ArchiveService {
	
	

	public void moveFileToArchive(Path file, Path archiveFolder, Path processingFolder) throws IOException {
		
		Path source = processingFolder.resolve(file.getFileName());
		
		Path destination = archiveFolder.resolve(file.getFileName());
		
		Files.move(source, destination, StandardCopyOption.ATOMIC_MOVE);
		
	}

}
