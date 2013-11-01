package es.com.blogspot.elblogdepicodev.plugintapestry.services.transaction;

public interface TransactionService {

	boolean beginIfNoPresent(TransactionDefinition definition);

	void begin(TransactionDefinition definition);

	void commit();

	void rollback();

	boolean isWithinTransaction();
}