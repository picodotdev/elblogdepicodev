package es.com.blogspot.elblogdepicodev.plugintapestry.services.transaction;

import org.apache.tapestry5.plastic.MethodAdvice;
import org.apache.tapestry5.plastic.MethodInvocation;

public class RequiredTransactionAdvice implements MethodAdvice {

	 private TransactionService service;

	 public RequiredTransactionAdvice(TransactionService service) {
		  this.service = service;
	 }

	 public void advise(MethodInvocation invocation) {
		  boolean isNew = service.beginIfNotPresent();

		  try {
				invocation.proceed();

				if (isNew) {
					 service.commit();
				}
		  } catch (Throwable e) {
				if (isNew) {
					 service.rollback();
				}
				throw e;
		  }
	 }
}