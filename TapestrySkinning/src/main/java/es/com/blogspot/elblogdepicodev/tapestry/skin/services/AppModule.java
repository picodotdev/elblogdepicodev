package es.com.blogspot.elblogdepicodev.tapestry.skin.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.AssetFactory;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.ContextProvider;
import org.apache.tapestry5.services.pageload.ComponentRequestSelectorAnalyzer;
import org.apache.tapestry5.services.pageload.ComponentResourceLocator;

public class AppModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(ComponentRequestSelectorAnalyzer.class, CustomComponentRequestSelectorAnalyzer.class).withId("CustomComponentRequestSelectorAnalyzer");
	}

	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
		String production = "false";
		configuration.add(SymbolConstants.PRODUCTION_MODE, production);
		configuration.add(SymbolConstants.COMPRESS_WHITESPACE, production);
		configuration.add(SymbolConstants.COMBINE_SCRIPTS, production);
		configuration.add(SymbolConstants.MINIFICATION_ENABLED, production);
		configuration.add(SymbolConstants.COMPACT_JSON, production);
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "es");
	}
	
	public static void contributeServiceOverride(MappedConfiguration<Class, Object> cfg, @InjectService("CustomComponentRequestSelectorAnalyzer") ComponentRequestSelectorAnalyzer analyzer) {
		cfg.add(ComponentRequestSelectorAnalyzer.class, analyzer);
	}

	public static Object decorateComponentResourceLocator(ComponentResourceLocator delegate, @ContextProvider AssetFactory assetFactory,
			ComponentClassResolver componentClassResolver) {
		return new CustomComponentResourceLocator(delegate);
	}
}