package es.com.blogspot.elblogdepicodev.tapestry.jpa.services;

import java.util.Date;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.beanvalidator.BeanValidatorConfigurer;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.jpa.JpaTransactionAdvisor;
import org.apache.tapestry5.services.ValidationDecoratorFactory;

import es.com.blogspot.elblogdepicodev.tapestry.jpa.dao.ProductoDAO;
import es.com.blogspot.elblogdepicodev.tapestry.jpa.dao.ProductoDAOImpl;
import es.com.blogspot.elblogdepicodev.tapestry.jpa.misc.AppValidationDecoratorFactory;
import es.com.blogspot.elblogdepicodev.tapestry.jpa.misc.DateTranslator;

public class AppModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(ValidationDecoratorFactory.class, AppValidationDecoratorFactory.class).withId("AppValidationDecoratorFactory");

		binder.bind(ProductoDAO.class, ProductoDAOImpl.class);
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

	public static void contributeServiceOverride(MappedConfiguration<Class, Object> configuration, @Local ValidationDecoratorFactory validationDecoratorFactory) {
		configuration.add(ValidationDecoratorFactory.class, validationDecoratorFactory);
	}

	public static void contributeTranslatorSource(MappedConfiguration configuration) {
		configuration.add(Date.class, new DateTranslator("yyyy-MM-dd"));
	}

	public static void contributeBeanValidatorSource(OrderedConfiguration<BeanValidatorConfigurer> configuration) {
		configuration.add("AppConfigurer", new BeanValidatorConfigurer() {
			public void configure(javax.validation.Configuration<?> configuration) {
				configuration.ignoreXmlConfiguration();
			}
		});
	}

	/**
	 * Los servicios con una interfaz *DAO (es necesario que sea una interfaz)
	 * soportan las anotaciones de transaccionalidad.
	 */
	@Match("*DAO")
	public static void adviseTransactionally(JpaTransactionAdvisor advisor, MethodAdviceReceiver receiver) {
		advisor.addTransactionCommitAdvice(receiver);
	}
}