package es.com.blogspot.elblogdepicodev.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.h2.tools.Server;

public class HelloWorld {

	public static void main(String[] args) throws Exception {
		Server server = null;
		try {
			server = Server.createTcpServer().start();

			ProcessEngines.init();

			ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti-mysql.cfg.xml").buildProcessEngine();

			RuntimeService runtimeService = processEngine.getRuntimeService();
			RepositoryService repositoryService = processEngine.getRepositoryService();

			repositoryService.createDeployment().addClasspathResource("bpmn/helloWorld/HelloWorld.bpmn20.xml").deploy();

			runtimeService.startProcessInstanceByKey("helloWorld");
		} finally {
			ProcessEngines.destroy();
			if (server != null)
				server.stop();
		}
	}
}