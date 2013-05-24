package es.com.blogspot.elblogdepicodev.tapestry.security.misc;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationDecorator;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.ValidationDecoratorFactory;

public class AppValidationDecoratorFactory implements ValidationDecoratorFactory {
	private final Environment environment;

	public AppValidationDecoratorFactory(Environment environment) {
		this.environment = environment;
	}

	public ValidationDecorator newInstance(MarkupWriter writer) {
		return new AppValidationDecorator(environment, writer);
	}
}
