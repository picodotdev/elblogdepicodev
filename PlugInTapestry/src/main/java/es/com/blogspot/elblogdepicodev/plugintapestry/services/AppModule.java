package es.com.blogspot.elblogdepicodev.plugintapestry.services;

import java.util.Date;

import javax.persistence.EntityManager;

import org.apache.shiro.realm.Realm;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.beanvalidator.BeanValidatorConfigurer;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.jpa.JpaTransactionAdvisor;
import org.apache.tapestry5.services.javascript.JavaScriptModuleConfiguration;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.tynamo.security.SecuritySymbols;
import org.tynamo.shiro.extension.realm.text.ExtendedPropertiesRealm;

import es.com.blogspot.elblogdepicodev.plugintapestry.entities.Producto;
import es.com.blogspot.elblogdepicodev.plugintapestry.misc.DateTranslator;
import es.com.blogspot.elblogdepicodev.plugintapestry.misc.PlugInStack;
import es.com.blogspot.elblogdepicodev.plugintapestry.services.dao.ProductoDAO;
import es.com.blogspot.elblogdepicodev.plugintapestry.services.dao.ProductoDAOImpl;

public class AppModule {

	 public static void bind(ServiceBinder binder) {
		  // Añadir al contenedor de dependencias nuestros servicios, se proporciona la interfaz y la
		  // implementación. Si tuviera un constructor con parámetros se inyectarían como
		  // dependencias.
		  //binder.bind(Sevicio.class, ServicioImpl.class);
	 }
	 
	 public static ProductoDAO buildProductoDAO(EntityManager entityManager) {
		  return new ProductoDAOImpl(Producto.class, entityManager);
	 }

	 public static void contributeApplicationDefaults(MappedConfiguration<String, Object> configuration) {
		  configuration.add(SymbolConstants.PRODUCTION_MODE, false);
		  configuration.add(SymbolConstants.SUPPORTED_LOCALES, "es,en");

		  configuration.add(SecuritySymbols.LOGIN_URL, "/login");
		  configuration.add(SecuritySymbols.SUCCESS_URL, "/index");
		  configuration.add(SecuritySymbols.UNAUTHORIZED_URL, "/unauthorized");
		  configuration.add(SecuritySymbols.REDIRECT_TO_SAVED_URL, "true");

		  configuration.add(SymbolConstants.APPLICATION_VERSION, "1.0");

		  configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
	 }

	 public static void contributeWebSecurityManager(Configuration<Realm> configuration) {
		  ExtendedPropertiesRealm realm = new ExtendedPropertiesRealm("classpath:shiro-users.properties");
		  configuration.add(realm);
	 }

	 public static void contributeTranslatorSource(MappedConfiguration configuration) {
		  configuration.add(Date.class, new DateTranslator("yyyy-MM-dd"));
	 }

	 public static void contributeModuleManager(MappedConfiguration<String, Object> configuration, @Path("classpath:META-INF/assets/app/jquery-library.js") Resource jQuery) {
		  configuration.override("jquery-library", new JavaScriptModuleConfiguration(jQuery));
	 }

	 public static void contributeBeanValidatorSource(OrderedConfiguration<BeanValidatorConfigurer> configuration) {
		  configuration.add("AppConfigurer", new BeanValidatorConfigurer() {
				public void configure(javax.validation.Configuration<?> configuration) {
					 configuration.ignoreXmlConfiguration();
				}
		  });
	 }

	 public static void contributeJavaScriptStackSource(MappedConfiguration<String, JavaScriptStack> configuration) {
		  configuration.addInstance("plugin", PlugInStack.class);
	 }

	 /**
	  * Hacer que los servicios DAO soporten la anotación de transaccionalidad CommitAfter.
	  */
	 @Match("*DAO")
	 public static void adviseTransactionally(JpaTransactionAdvisor advisor, MethodAdviceReceiver receiver) {
		  advisor.addTransactionCommitAdvice(receiver);
	 }
}