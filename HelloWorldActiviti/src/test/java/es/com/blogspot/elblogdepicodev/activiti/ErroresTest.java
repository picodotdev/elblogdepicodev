package es.com.blogspot.elblogdepicodev.activiti;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import es.com.blogspot.elblogdepicodev.activiti.misc.Producto;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ErroresTest extends ActivitiAbstractTest {

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule("activiti-h2.cfg.xml");

	@Test
	@Deployment(resources = "bpmn/Errores.bpmn20.xml")
	public void testHayExistencias() {
		Producto producto = new Producto("Arch Linux T-Shirt", 10l);

		Map variables = new HashMap();
		variables.put("producto", producto);

		RuntimeService rs = activitiRule.getRuntimeService();
		rs.startProcessInstanceByKey("errores", variables);

		Assert.assertEquals(new Long(9), producto.getExistencias());
	}

	@Test
	@Deployment(resources = "bpmn/Errores.bpmn20.xml")
	public void testNoHayExistencias() {
		Producto producto = new Producto("Arch Linux Mug", 0l);

		Map variables = new HashMap();
		variables.put("producto", producto);

		RuntimeService rs = activitiRule.getRuntimeService();
		rs.startProcessInstanceByKey("errores", variables);

		Assert.assertEquals(new Long(0), producto.getExistencias());
	}
}