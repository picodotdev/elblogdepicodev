package es.com.blogspot.elblogdepicodev.activiti;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.h2.tools.Server;

import es.com.blogspot.elblogdepicodev.activiti.misc.Cliente;
import es.com.blogspot.elblogdepicodev.activiti.misc.Cliente.Tipo;

public class ReglasDeNegocio {

	public static void main(String[] args) throws Exception {
		Server server = null;
		try {
			server = Server.createTcpServer().start();

			ProcessEngines.init();

			ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti-mysql.cfg.xml").buildProcessEngine();

			RuntimeService runtimeService = processEngine.getRuntimeService();
			RepositoryService repositoryService = processEngine.getRepositoryService();

			repositoryService.createDeployment().addClasspathResource("bpmn/ReglasDeNegocio.bpmn20.xml").addClasspathResource("rules/TipoCliente.drl")
					.addClasspathResource("rules/Descuento.drl").addClasspathResource("rules/TipoEnvio.drl").deploy();

			Cliente cliente = new Cliente(Tipo.NORMAL);
			Map variables = new HashMap();
			Map output = new HashMap();
			variables.put("cliente", cliente);
			variables.put("importe", new BigDecimal("150"));
			variables.put("output", output);

			ProcessInstance pi = runtimeService.startProcessInstanceByKey("reglasDeNegocio", variables);

			System.out.println("Tipo cliente: " + cliente.getTipo());
			System.out.println("Descuento aplicado: " + output.get("descuento"));
		} finally {
			ProcessEngines.destroy();
			if (server != null)
				server.stop();
		}
	}
}