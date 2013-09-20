package es.com.blogspot.elblogdepicodev.properties;

import java.util.ResourceBundle;

public class Main {

	 public static void main(String[] args) {
		  // Cargar un archivo properties en ISO-8859-1. Los caracteres del String obtenido de prueba se cargan correctamente.
		  {
				ResourceBundle bundle = ResourceBundle.getBundle("default");

				String s = bundle.getString("prueba");
				System.out.println(s);
		  }

		  // Cargar un archivo properties UTF-8 con codificación ISO-8859-1. Los caracteres del String obtenido de prueba NO se cargan correctamente.
		  {
				ResourceBundle bundle = ResourceBundle.getBundle("utf8-1");

				String s = bundle.getString("prueba");
				System.out.println(s);
		  }

		  // Cargar un archivo properties en UTF-8. Los caracteres del String obtenido de prueba se cargan correctamente.
		  {
				ResourceBundle bundle = ResourceBundle.getBundle("utf8-2", new EncodingControl("UTF-8"));

				String s = bundle.getString("prueba");
				System.out.println(s);
		  }
	 }
}