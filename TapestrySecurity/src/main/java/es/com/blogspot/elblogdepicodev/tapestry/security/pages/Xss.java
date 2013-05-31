package es.com.blogspot.elblogdepicodev.tapestry.security.pages;

import java.text.Format;
import java.text.MessageFormat;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;

public class Xss {

	@Inject
	@Symbol(SymbolConstants.TAPESTRY_VERSION)
	@Property
	private String tapestryVersion;

	@Persist(PersistenceConstants.FLASH)
	@Property
	private String dato;
	
	public Object[] getDatos() {
		return new Object[] { dato };
	}
	
	public Format getFormat() {
		return new MessageFormat("{0}");		
	}
}