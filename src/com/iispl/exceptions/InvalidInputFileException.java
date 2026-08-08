package com.iispl.exceptions;

public class InvalidInputFileException extends Exception {

	public InvalidInputFileException() {
		super("InvalidInputFileException input file is not a regular file");
	}
	
}
