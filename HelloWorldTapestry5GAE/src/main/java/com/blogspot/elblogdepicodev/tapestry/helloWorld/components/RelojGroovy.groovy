package com.blogspot.elblogdepicodev.tapestry.helloWorld.components

import org.apache.tapestry5.Asset
import org.apache.tapestry5.ComponentResources
import org.apache.tapestry5.MarkupWriter
import org.apache.tapestry5.annotations.Environmental
import org.apache.tapestry5.annotations.Path
import org.apache.tapestry5.annotations.SupportsInformalParameters
import org.apache.tapestry5.ioc.annotations.Inject
import org.apache.tapestry5.json.JSONObject
import org.apache.tapestry5.services.javascript.JavaScriptSupport

@SupportsInformalParameters
public class RelojGroovy {

    @Inject
    @Path("classpath:com/blogspot/elblogdepicodev/tapestry/helloWorld/components/Reloj.js")
    private Asset script

    @Environmental
    private JavaScriptSupport javaScriptSupport
    
    @Inject
    private ComponentResources componentResources

    protected void setupRender() {
        javaScriptSupport.importJavaScriptLibrary(script)
    }

    protected boolean beginRender(MarkupWriter writer) {
        def id = componentResources.id
        
        writer.element("span", "id", id)
        componentResources.renderInformalParameters(writer)
		return false
    }

    protected boolean afterRender(MarkupWriter writer) {
		writer.end()
		
        JSONObject spec = new JSONObject()
        spec.put("id", componentResources.id)
        
        javaScriptSupport.addInitializerCall("reloj", spec)       
		return true
    }
}
