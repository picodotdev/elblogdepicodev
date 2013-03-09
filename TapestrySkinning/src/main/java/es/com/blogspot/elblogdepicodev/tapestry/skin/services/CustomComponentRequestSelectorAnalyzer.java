package es.com.blogspot.elblogdepicodev.tapestry.skin.services;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.services.pageload.ComponentRequestSelectorAnalyzer;
import org.apache.tapestry5.services.pageload.ComponentResourceSelector;

public class CustomComponentRequestSelectorAnalyzer implements ComponentRequestSelectorAnalyzer {

	private final ThreadLocale threadLocale;
	private final HttpServletRequest request;
	
	public CustomComponentRequestSelectorAnalyzer(ThreadLocale threadLocale, HttpServletRequest request) {
		this.threadLocale = threadLocale;
		this.request = request;
	}

	@Override
	public ComponentResourceSelector buildSelectorForRequest() {
		Locale locale = threadLocale.getLocale();
		DominioAxis dominio = DominioAxis.getByServerName(request.getServerName());
		return new ComponentResourceSelector(locale).withAxis(DominioAxis.class, dominio);
	}
}