package es.com.blogspot.elblogdepicodev.resteasy;

import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public class HelloWorldResourceClient implements HelloWorldResource {
	
	private HelloWorldResource client;
	
	public HelloWorldResourceClient() {
		// Obtener el cliente a partir de la interfaz y de donde está localizado
		client = ProxyFactory.create(HelloWorldResource.class, "http://localhost:8080/helloworld-resteasy/rest");
	}
	
	@Override
	public String getSaluda() {
		return client.getSaluda();
	}

	@Override
	public String getSaludaA(String nombre) {
		return client.getSaludaA(nombre);
	}
	
	@Override
	public Mensaje getMensajeJSON(String nombre) {
		return client.getMensajeJSON(nombre);
	}
	
	@Override
	public Mensaje getMensajeXML(String nombre) {
		return client.getMensajeXML(nombre);
	}

	public static void main(String[] args) {
		// Inicializacion a realizar una vez por máquina virtual
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
		
		HelloWorldResourceClient client = new HelloWorldResourceClient();
		System.out.println(client.getSaluda());
		System.out.println(client.getSaludaA("picodotdev"));
		System.out.println(client.getMensajeJSON("picodotdev"));
		System.out.println(client.getMensajeXML("picodotdev"));
	}
}