package es.com.blogspot.elblogdepicodev.plugintapestry.test.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.services.LibraryMapping;

public class TestModule {

	 public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
		  configuration.add(new LibraryMapping("test", "es.com.blogspot.elblogdepicodev.plugintapestry.test"));
	 }
}