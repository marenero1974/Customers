package it.customers.h2db.springboot.repositry;

import it.customers.h2db.springboot.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
