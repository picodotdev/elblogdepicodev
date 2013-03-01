package es.com.blogspot.elblogdepicodev.tapestry.resteasy.services;

import java.util.Collection;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ScopeConstants;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.HttpServletRequestFilter;

import es.com.blogspot.elblogdepicodev.resteasy.Application;
import es.com.blogspot.elblogdepicodev.resteasy.HelloWorldResource;
import es.com.blogspot.elblogdepicodev.resteasy.HelloWorldResourceImpl;

public class AppModule {

	// Servicios autoconstruidos por Tapestry, Tapestry se encarga de inyectar
	// las dependencias que necesiten los servicios de forma automática, ademas
	// se encarga del «live class reloading» cuando se hagan modificaciones en
	// la clase
	public static void bind(ServiceBinder binder) {
		// Servicio que inyectaremos como dependencia al servicio REST
		// HelloWorldResource
		binder.bind(ContadorService.class, ContadorServiceImpl.class).scope(ScopeConstants.DEFAULT);

		// La dependencia en el contructor sobre ContadorService es inyectada
		// automáticamente por Tapestry. El servicio inyectado podría ser
		// cualquier otro que esté definido en el contenedor IoC de Tapestry
		// como por ejemplo podría ser el servicio de persistencia (EntityManager).
		binder.bind(HelloWorldResource.class, HelloWorldResourceImpl.class);

		// Filtro de integración con RESTEasy
		binder.bind(HttpServletRequestFilter.class, ResteasyRequestFilter.class).withId("ResteasyRequestFilter");
	}

	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
		String production = "false";
		configuration.add(SymbolConstants.PRODUCTION_MODE, production);
		configuration.add(SymbolConstants.COMPRESS_WHITESPACE, production);
		configuration.add(SymbolConstants.COMBINE_SCRIPTS, production);
		configuration.add(SymbolConstants.MINIFICATION_ENABLED, production);
		configuration.add(SymbolConstants.COMPACT_JSON, production);
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "es");

		// Contribuciones que definirán las rutas en las que el filtro de
		// RESTEasy atenderá las peticiones REST
		configuration.add(ResteasySymbols.MAPPING_PREFIX, "/rest");
		configuration.add(ResteasySymbols.MAPPING_PREFIX_JSAPI, "/rest-jsapi");
	}

	// Añadir el filtro de RESTEasy al pipeline de Tapestry
	public static void contributeHttpServletRequestHandler(OrderedConfiguration<HttpServletRequestFilter> configuration,
			@InjectService("ResteasyRequestFilter") HttpServletRequestFilter resteasyRequestFilter) {
		configuration.add("ResteasyRequestFilter", resteasyRequestFilter, "after:IgnoredPaths", "before:GZIP");
	}

	public static void contributeApplication(Configuration<Object> singletons, HelloWorldResource helloWorldResource) {
		// Contribuir a la configuración del servicio Application con los
		// servicios REST
		singletons.add(helloWorldResource);
	}

	// Otra forma de definir un servicio, la colección de singletos proviene de
	// las contribuciones hechas en contributeApplication
	public static Application buildApplication(Collection<Object> singletons) {
		return new Application(singletons);
	}
}