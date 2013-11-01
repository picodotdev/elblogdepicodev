package es.com.blogspot.elblogdepicodev.plugintapestry.services.transaction;

import java.util.Stack;

import org.apache.tapestry5.ioc.services.PerthreadManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateTransactionServiceImpl implements TransactionService {

	 private Session session;
	 private Stack<Transaction> transactionStack;

	 public HibernateTransactionServiceImpl(Session session, PerthreadManager perthreadManager) {
		  this.session = session;
		  this.transactionStack = new Stack<Transaction>();
		  
		  perthreadManager.addThreadCleanupCallback(new Runnable() {
				@Override
				public void run() {
					 cleanup();
				}				
		  });
	 }

	 public void begin() {
		  Transaction transaction = session.beginTransaction();
		  transactionStack.push(transaction);
	 }

	 public void commit() {
		  if (isWithinTransaction()) {
				transactionStack.pop().commit();
				begin();
		  }
	 }

	 public void rollback() {
		  if (isWithinTransaction()) {
				transactionStack.pop().rollback();
		  }
	 }

	 public boolean isWithinTransaction() {
		  return !transactionStack.empty();
	 }

	 public boolean beginIfNotPresent() {
		  if (isWithinTransaction()) {
				return false;
		  }
		  begin();
		  return true;
	 }
	 
	 private void cleanup() {
		  for (Transaction transaction : transactionStack) {
				transaction.rollback();
		  }
	 }
}