package es.com.blogspot.elblogdepicodev.plugintapestry.services.transaction;

import java.lang.reflect.Method;

import org.apache.tapestry5.ioc.MethodAdviceReceiver;

public class TransactionAdvisorImpl implements TransactionAdvisor {
	 private TransactionService service;

	 public TransactionAdvisorImpl(TransactionService service) {
		  this.service = service;
	 }

	 public void addTransactionAdvice(final MethodAdviceReceiver receiver) {
		  for (Method method : receiver.getInterface().getMethods()) {
				Transactional transactional = method.getAnnotation(Transactional.class);

				if (transactional != null) {
					 adviceMethod(transactional.propagation(), method, receiver);
				}
		  }
	 }

	 private void adviceMethod(final Propagation propagation, Method method, MethodAdviceReceiver receiver) {
		  switch (propagation) {
				case REQUIRED:
					 receiver.adviseMethod(method, new RequiredTransactionAdvice(service));
					 break;
				case MANDATORY:
					 receiver.adviseMethod(method, new MandatoryTransactionAdvice(service));
					 break;
				case NESTED:
					 receiver.adviseMethod(method, new NestedTransactionAdvice(service));
					 break;
				case NEVER:
					 receiver.adviseMethod(method, new NeverTransactionAdvice(service));
					 break;
				case SUPPORTS:
					 break;
		  }
	 }
}