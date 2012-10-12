package es.com.blogspot.elblogdepicodev.activiti;

import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

public class HelloWorldTest extends ActivitiAbstractTest {

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule("activiti-h2.cfg.xml");

	@Test
	@Deployment(resources = "bpmn/HelloWorld.bpmn20.xml")
	public void test1() {
		activitiRule.getRuntimeService().startProcessInstanceByKey("helloWorld");

		System.out.println("Hola Mundo desde el test!");
	}
}