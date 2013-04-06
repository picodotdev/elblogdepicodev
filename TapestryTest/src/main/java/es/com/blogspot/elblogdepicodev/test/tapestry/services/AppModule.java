package es.com.blogspot.elblogdepicodev.test.tapestry.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;

public class AppModule {

	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
		String production = "false";
		configuration.add(SymbolConstants.PRODUCTION_MODE, production);
		configuration.add(SymbolConstants.COMPRESS_WHITESPACE, production);
		configuration.add(SymbolConstants.COMBINE_SCRIPTS, production);
		configuration.add(SymbolConstants.MINIFICATION_ENABLED, production);
		configuration.add(SymbolConstants.COMPACT_JSON, production);
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "es");
	}
	
	public static void bind(ServiceBinder binder) {
		binder.bind(MensajeService.class, MensajeServiceImpl.class);
	}
}