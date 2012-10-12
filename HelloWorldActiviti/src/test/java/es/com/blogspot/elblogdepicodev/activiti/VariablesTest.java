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
public class VariablesTest extends ActivitiAbstractTest {

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule("activiti-h2.cfg.xml");

	@Test
	@Deployment(resources = "bpmn/Variables.bpmn20.xml")
	public void testNormal() {
		Cliente cliente = new Cliente(Tipo.NORMAL);
		Map output = new HashMap();

		Map variables = new HashMap();
		variables.put("cliente", cliente);
		variables.put("output", output);

		RuntimeService rs = activitiRule.getRuntimeService();
		rs.startProcessInstanceByKey("variables", variables);

		Assert.assertEquals(new BigDecimal("0"), output.get("descuento"));
	}

	@Test
	@Deployment(resources = "bpmn/Variables.bpmn20.xml")
	public void testVIP() {
		Cliente cliente = new Cliente(Tipo.VIP);
		Map output = new HashMap();

		Map variables = new HashMap();
		variables.put("cliente", cliente);
		variables.put("output", output);

		RuntimeService rs = activitiRule.getRuntimeService();
		rs.startProcessInstanceByKey("variables", variables);

		Assert.assertEquals(new BigDecimal("10"), output.get("descuento"));
	}
}