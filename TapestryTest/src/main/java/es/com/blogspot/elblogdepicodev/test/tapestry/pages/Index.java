package es.com.blogspot.elblogdepicodev.test.tapestry.pages;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;

public class Index {

	@Inject
	@Symbol(SymbolConstants.TAPESTRY_VERSION)
	@Property
	private String tapestryVersion;	
}