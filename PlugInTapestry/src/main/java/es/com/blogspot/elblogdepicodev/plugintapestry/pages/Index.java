package es.com.blogspot.elblogdepicodev.plugintapestry.pages;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.com.blogspot.elblogdepicodev.plugintapestry.entities.Producto;
import es.com.blogspot.elblogdepicodev.plugintapestry.services.dao.ProductoDAO;

/**
 * @tapestrydoc
 */
public class Index {

	 @Property
	 @Persist(value = PersistenceConstants.SESSION)
	 private Long cuenta;

	 @Inject
	 private ProductoDAO dao;

	 @Property
	 private Producto producto;
	 
	 @Component
	 private Zone zone;

	 // Ciclo de vida
	 Object onActivate(String context) {
		  if (context != null) {
				return Error404.class;
		  }
		  return null;
	 }

	 /**
	  * Método del ciclo de vida de la página que es llamado por Tapestry al inicio de la
	  * renderización de la página.
	  */
	 void setupRender() {
		  if (cuenta == null) {
				// Iniciarlizar el valor de la cuenta al cargar la página
				cuenta = 0l;
		  }
	 }

	 // Eventos
	 /**
	  * Esta acción permite ver el informe de error generado por Tapestry, además de la traza de la
	  * excepción, el informe incluye mucha más información (extracto de la plantilla, información de
	  * la petición, del entorno, ...)
	  */
	 void onActionFromInformeError() throws Exception {
		  throw new Exception("Sí, ese enlace produce una excepción");
	 }

	 /**
	  * Evento que suma uno a la cuenta.
	  */
	 void onActionFromSumarCuenta1() throws Exception {
		  cuenta += 1;
	 }
	 
	 /**
	  * Evento que suma uno a la cuenta (via Ajax).
	  */
	 Object onActionFromSumarCuenta1Ajax() throws Exception {
		  cuenta += 1;
		  return zone.getBody();
	 }

	 /**
	  * Evento que reinicializa la cuenta.
	  */
	 @RequiresPermissions("cuenta:reset")
	 void onActionFromReiniciarCuenta() throws Exception {
		  cuenta = 0l;
	 }

	 /**
	  * Evento que cierra la sesión del usuario actual.
	  */
	 void onActionFromCerrarSesion() throws Exception {
		  SecurityUtils.getSecurityManager().logout(getSubject());
	 }

	 /**
	  * Devuelve el objeto que representa al usuario autenticado.
	  */
	 public WebDelegatingSubject getSubject() {
		  return (WebDelegatingSubject) SecurityUtils.getSubject();
	 }
}