package es.com.blogspot.elblogdepicodev.activiti;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import es.com.blogspot.elblogdepicodev.activiti.misc.Cliente;
import es.com.blogspot.elblogdepicodev.activiti.misc.Cliente.Tipo;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReglasDeNegocioTest extends ActivitiAbstractTest {

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule("activiti-h2.cfg.xml");

	@Test
	@Deployment(resources = { "bpmn/ReglasDeNegocio.bpmn20.xml", "rules/TipoCliente.drl", "rules/Descuento.drl", "rules/TipoEnvio.drl" })
	public void testNormal() {
		Cliente cliente = new Cliente();
		Map output = new HashMap();

		Map variables = new HashMap();
		variables.put("cliente", cliente);
		variables.put("importe", new BigDecimal("50"));
		variables.put("output", output);

		RuntimeService rs = activitiRule.getRuntimeService();
		rs.startProcessInstanceByKey("reglasDeNegocio", variables);

		Assert.assertEquals(cliente.getTipo(), Tipo.NORMAL);
		Assert.assertEquals(BigDecimal.ZERO, output.get("descuento"));
		Assert.assertEquals("normal", output.get("tipoEnvio"));
	}

	@Test
	@Deployment(resources = { "bpmn/ReglasDeNegocio.bpmn20.xml", "rules/TipoCliente.drl", "rules/Descuento.drl", "rules/TipoEnvio.drl" })
	public void testVIP() {
		Cliente cliente = new Cliente();
		Map output = new HashMap();

		Map variables = new HashMap();
		variables.put("cliente", cliente);
		variables.put("importe", new BigDecimal("150"));
		variables.put("output", output);

		RuntimeService rs = activitiRule.getRuntimeService();
		rs.startProcessInstanceByKey("reglasDeNegocio", variables);

		Assert.assertEquals(cliente.getTipo(), Tipo.VIP);
		Assert.assertEquals(new BigDecimal("10"), output.get("descuento"));
		Assert.assertEquals("urgente", output.get("tipoEnvio"));
	}
}