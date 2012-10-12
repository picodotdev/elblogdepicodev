package es.com.blogspot.elblogdepicodev.activiti;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.h2.tools.Server;

import es.com.blogspot.elblogdepicodev.activiti.misc.Producto;

public class Errores {

	public static void main(String[] args) throws Exception {
		Server server = null;
		try {
			server = Server.createTcpServer().start();

			ProcessEngines.init();

			ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti-mysql.cfg.xml").buildProcessEngine();

			RuntimeService runtimeService = processEngine.getRuntimeService();
			RepositoryService repositoryService = processEngine.getRepositoryService();

			repositoryService.createDeployment().addClasspathResource("bpmn/Errores.bpmn20.xml").deploy();

			Producto producto = new Producto("Arch Linux T-Shirt", 10l);
			Map variables = new HashMap();
			variables.put("producto", producto);

			ProcessInstance pi = runtimeService.startProcessInstanceByKey("errores", variables);

			System.out.println(MessageFormat.format("Las nuevas existencias de {0} son {1}", producto.getNombre(), producto.getExistencias()));
		} finally {
			ProcessEngines.destroy();
			if (server != null)
				server.stop();
		}
	}
}