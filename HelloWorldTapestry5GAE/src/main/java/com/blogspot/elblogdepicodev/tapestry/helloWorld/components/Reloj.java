package com.blogspot.elblogdepicodev.tapestry.helloWorld.components;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@SupportsInformalParameters
public class Reloj {

    @Inject
    @Path("classpath:com/blogspot/elblogdepicodev/tapestry/helloWorld/components/Reloj.js")
    private Asset script;

    @Environmental
    private JavaScriptSupport javaScriptSupport;
    
    @Inject
    private ComponentResources componentResources;

    void setupRender() {
    	javaScriptSupport.importJavaScriptLibrary(script);
    }

    boolean beginRender(MarkupWriter writer) {
        String id = componentResources.getId();
        
        writer.element("span", "id", id);
        componentResources.renderInformalParameters(writer);
        return false;
    }

    boolean afterRender(MarkupWriter writer) {
        writer.end();
        
        JSONObject spec = new JSONObject();
        spec.put("id", componentResources.getId());
        
        javaScriptSupport.addInitializerCall("reloj", spec);
        return true;
    }
}
