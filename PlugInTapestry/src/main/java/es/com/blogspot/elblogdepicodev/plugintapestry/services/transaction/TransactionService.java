package es.com.blogspot.elblogdepicodev.plugintapestry.services.transaction;

public interface TransactionService {

	 boolean beginIfNotPresent();

	 void begin();

	 void commit();

	 void rollback();

	 boolean isWithinTransaction();
}