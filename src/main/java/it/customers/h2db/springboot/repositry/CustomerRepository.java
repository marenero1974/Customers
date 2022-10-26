package it.customers.h2db.springboot.repositry;

import it.customers.h2db.springboot.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

/*  @Query("SELECT c FROM Customer c WHERE c.codiceFiscale = ?1")*/
  Customer findByCodiceFiscale(String codiceFiscale);
}
