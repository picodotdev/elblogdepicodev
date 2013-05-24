package es.com.blogspot.elblogdepicodev.tapestry.security.services;

import org.apache.shiro.realm.Realm;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.services.ValidationDecoratorFactory;
import org.tynamo.security.SecuritySymbols;
import org.tynamo.security.services.SecurityFilterChainFactory;
import org.tynamo.security.services.impl.SecurityFilterChain;
import org.tynamo.shiro.extension.realm.text.ExtendedPropertiesRealm;

import es.com.blogspot.elblogdepicodev.tapestry.security.misc.AppValidationDecoratorFactory;

public class AppModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(ValidationDecoratorFactory.class, AppValidationDecoratorFactory.class).withId("AppValidationDecoratorFactory");
	}

	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
		String production = "false";
		configuration.add(SymbolConstants.PRODUCTION_MODE, production);
		configuration.add(SymbolConstants.COMPRESS_WHITESPACE, production);
		configuration.add(SymbolConstants.COMBINE_SCRIPTS, production);
		configuration.add(SymbolConstants.MINIFICATION_ENABLED, production);
		configuration.add(SymbolConstants.COMPACT_JSON, production);
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "es");

		configuration.add(SecuritySymbols.LOGIN_URL, "/login");
		configuration.add(SecuritySymbols.SUCCESS_URL, "/index");
		configuration.add(SecuritySymbols.UNAUTHORIZED_URL, "/unauthorized");
		configuration.add(SecuritySymbols.REDIRECT_TO_SAVED_URL, "true");
	}

	public static void contributeServiceOverride(MappedConfiguration<Class, Object> configuration, @Local ValidationDecoratorFactory validationDecoratorFactory) {
		configuration.add(ValidationDecoratorFactory.class, validationDecoratorFactory);
	}

	public static void contributeWebSecurityManager(Configuration<Realm> configuration) {
		ExtendedPropertiesRealm realm = new ExtendedPropertiesRealm("classpath:shiro-users.properties");
		configuration.add(realm);
	}

	public static void contributeSecurityConfiguration(Configuration<SecurityFilterChain> configuration, SecurityFilterChainFactory factory) {
		configuration.add(factory.createChainWithRegEx("^*/login*$").add(factory.anon()).build());
		configuration.add(factory.createChainWithRegEx("^*/index*$").add(factory.authc()).build());
	}
}