package es.com.blogspot.elblogdepicodev.plugintapestry.services.transaction;

import org.apache.tapestry5.plastic.MethodAdvice;
import org.apache.tapestry5.plastic.MethodInvocation;

public class NestedTransactionAdvice implements MethodAdvice {
 
   private TransactionService service;
 
   public NestedTransactionAdvice(TransactionService service) {
      this.service = service;
   }
 
   public void advise(MethodInvocation invocation) {
      try{
         service.begin();
         invocation.proceed();
         service.commit();
      }catch(Exception ex){
         service.rollback();
         throw new RuntimeException(ex);
      }
   }
}