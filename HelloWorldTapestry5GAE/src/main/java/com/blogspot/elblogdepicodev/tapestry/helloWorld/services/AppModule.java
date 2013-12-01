package com.blogspot.elblogdepicodev.tapestry.helloWorld.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;

public class AppModule {
    public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
    	String production = "true";
		configuration.add(SymbolConstants.PRODUCTION_MODE, production);
		configuration.add(SymbolConstants.COMPRESS_WHITESPACE, production);
		configuration.add(SymbolConstants.COMBINE_SCRIPTS, production);
		configuration.add(SymbolConstants.MINIFICATION_ENABLED, production);
		configuration.add(SymbolConstants.COMPACT_JSON, production);
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "es");
    }
}