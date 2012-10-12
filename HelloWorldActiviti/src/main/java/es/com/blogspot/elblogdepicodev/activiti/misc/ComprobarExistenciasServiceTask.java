package es.com.blogspot.elblogdepicodev.activiti.misc;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;

public class ComprobarExistenciasServiceTask implements ActivityBehavior {

	@Override
	public void execute(ActivityExecution execution) throws Exception {
		PvmTransition transition = null;
		Producto producto = (Producto) execution.getVariable("producto");
		if (producto.hayExistencias()) {
			transition = execution.getActivity().findOutgoingTransition("flowHayExistencias");
		} else {
			transition = execution.getActivity().findOutgoingTransition("flowNoHayExistencias");
		}
		execution.take(transition);
	}
}