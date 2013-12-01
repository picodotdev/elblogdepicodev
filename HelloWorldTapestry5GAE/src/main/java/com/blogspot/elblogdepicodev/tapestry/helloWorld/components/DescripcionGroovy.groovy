package com.blogspot.elblogdepicodev.tapestry.helloWorld.components

import org.apache.tapestry5.ComponentResources
import org.apache.tapestry5.ioc.annotations.Inject

class DescripcionGroovy {

	@Inject
	private ComponentResources componentResources
	
	boolean enPaginaJava(){
		return componentResources.pageName == 'Index'		
	}
}