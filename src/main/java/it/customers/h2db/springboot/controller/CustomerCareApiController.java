package it.customers.h2db.springboot.controller;

import it.customers.h2db.springboot.dto.InserimentoCustomerRequest;
import it.customers.h2db.springboot.dto.InserimentoCustomerResponse;
import it.customers.h2db.springboot.mapper.CustomerMapper;
import it.customers.h2db.springboot.models.Customer;
import it.customers.h2db.springboot.repositry.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-25T15:25:16.139816300+02:00[Europe/Rome]")
@Controller
@RequestMapping("${openapi.customerCareApplication.base-path:}")
public class CustomerCareApiController implements CustomerCareApi {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public ResponseEntity<InserimentoCustomerResponse> inserisciCustomer(InserimentoCustomerRequest input) {

        Customer customer = CustomerMapper.mapToEntity(input);

        if(customer.getDeviceList().size() > 2) {
            InserimentoCustomerResponse response = new InserimentoCustomerResponse();
            response.setSuccess(false);
            response.setMessage("Il numero di device per un utente non deve essere maggiore di 2");
            return ResponseEntity.internalServerError().body(response);
        }

        customerRepository.save(customer);

        InserimentoCustomerResponse response = new InserimentoCustomerResponse();
        response.setSuccess(true);
        response.setMessage("Customer inserito correttamente");

        return ResponseEntity.ok().body(response);
    }
}
