package es.com.blogspot.elblogdepicodev.activiti;

import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.activiti.engine.impl.util.LogUtil;
import org.junit.BeforeClass;
import org.slf4j.bridge.SLF4JBridgeHandler;

public abstract class ActivitiAbstractTest {

	@BeforeClass
	public static void beforeClass() {
		// Integrar SLF4J con Activiti
		LogUtil.readJavaUtilLoggingConfigFromClasspath();

		Logger rootLogger = LogManager.getLogManager().getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		for (Handler h : handlers) {
			rootLogger.removeHandler(h);
		}
		SLF4JBridgeHandler.install();
	}
}