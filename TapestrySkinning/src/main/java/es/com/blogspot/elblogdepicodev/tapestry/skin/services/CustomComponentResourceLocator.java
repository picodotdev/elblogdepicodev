package es.com.blogspot.elblogdepicodev.tapestry.skin.services;

import java.util.List;

import org.apache.tapestry5.TapestryConstants;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.model.ComponentModel;
import org.apache.tapestry5.services.pageload.ComponentResourceLocator;
import org.apache.tapestry5.services.pageload.ComponentResourceSelector;

public class CustomComponentResourceLocator implements ComponentResourceLocator {

	private final ComponentResourceLocator delegate;

	public CustomComponentResourceLocator(ComponentResourceLocator delegate) {
		this.delegate = delegate;
	}

	@Override
	public Resource locateTemplate(ComponentModel model, ComponentResourceSelector selector) {
		DominioAxis dominio = selector.getAxis(DominioAxis.class);

		if (dominio != null) {
			String skin = dominio.name().toLowerCase();
			String file = model.getBaseResource().getFile().replaceFirst("\\.class", "");
			String f = String.format("%s_%s.%s", file, skin, TapestryConstants.TEMPLATE_EXTENSION);
			Resource resource = model.getBaseResource().forFile(f);
			if (resource.exists()) {
				return resource.forLocale(selector.locale);
			}
		}

		return delegate.locateTemplate(model, selector);
	}

	@Override
	public List<Resource> locateMessageCatalog(Resource baseResource, ComponentResourceSelector selector) {
		return delegate.locateMessageCatalog(baseResource, selector);
	}
}