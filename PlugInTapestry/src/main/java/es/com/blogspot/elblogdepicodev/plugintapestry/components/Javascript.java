package es.com.blogspot.elblogdepicodev.plugintapestry.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * @tapestrydoc
 */
public class Javascript {

	 @Parameter(defaultPrefix = BindingConstants.LITERAL)
	 @Property
	 private String selector;

	 @Parameter(defaultPrefix = BindingConstants.LITERAL)
	 @Property
	 private String mensaje;

	 @Environmental
	 private JavaScriptSupport support;

	 void setupRender() {
		  JSONObject o = new JSONObject();
		  o.put("selector", selector);
		  o.put("mensaje", mensaje);

		  support.require("app/saludador").invoke("init").with(o);
	 }
}