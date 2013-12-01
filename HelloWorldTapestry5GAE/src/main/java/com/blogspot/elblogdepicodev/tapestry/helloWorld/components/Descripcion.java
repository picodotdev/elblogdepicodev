package com.blogspot.elblogdepicodev.tapestry.helloWorld.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Descripcion {

	@Inject
	private ComponentResources componentResources;
	
	public boolean enPaginaJava(){
		return componentResources.getPageName().equals("Index");		
	}
}