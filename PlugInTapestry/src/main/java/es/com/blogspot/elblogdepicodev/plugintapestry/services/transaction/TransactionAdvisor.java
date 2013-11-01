package es.com.blogspot.elblogdepicodev.plugintapestry.services.transaction;

import org.apache.tapestry5.ioc.MethodAdviceReceiver;

public interface TransactionAdvisor {
	 void addTransactionAdvice(MethodAdviceReceiver methodAdviceReceiver);
}