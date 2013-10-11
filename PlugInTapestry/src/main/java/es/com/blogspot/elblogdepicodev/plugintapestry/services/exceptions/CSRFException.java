package es.com.blogspot.elblogdepicodev.plugintapestry.services.exceptions;

public class CSRFException extends Exception {

	 private static final long serialVersionUID = -5205940043310676114L;

	 public CSRFException() {
	 }

	 public CSRFException(String message) {
		  super(message);
	 }
}