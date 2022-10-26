package it.customers.h2db.springboot.controller;

import it.customers.h2db.springboot.dto.CustomerRequest;
import it.customers.h2db.springboot.dto.CustomerResponse;
import it.customers.h2db.springboot.dto.InserimentoCustomerRequest;
import it.customers.h2db.springboot.mapper.CustomerMapper;
import it.customers.h2db.springboot.models.Customer;
import it.customers.h2db.springboot.repositry.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-25T15:25:16.139816300+02:00[Europe/Rome]")
@Controller
@RequestMapping("${openapi.customerCareApplication.base-path:}")
public class CustomerCareApiController implements CustomerCareApi {

    @Autowired
    CustomerRepository customerRepository;

    private static Logger logger = LoggerFactory.getLogger(CustomerCareApiController.class);


    @Override
    public ResponseEntity<CustomerResponse> inserisciCustomer(InserimentoCustomerRequest input) {
        logger.info("Inserimento di un customer {}", input);
        Customer customer = CustomerMapper.mapToEntity(input);

        if(customer.getDeviceList().size() > 2) {
            CustomerResponse response = new CustomerResponse();
            response.setSuccess(false);
            response.setMessage("Il numero di device per un utente non deve essere maggiore di 2");
            logger.error("Il numero di device per un utente non deve essere maggiore di 2");
            return ResponseEntity.internalServerError().body(response);
        }

        try {
            customerRepository.save(customer);
            logger.info("Inserimento di un customer completato.");
        } catch (Exception e) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Errore durante il salvataggio del customer:");
            errorMessage.append(e.getMessage());
            logger.error(errorMessage.toString());
            CustomerResponse response = new CustomerResponse();
            response.setSuccess(false);
            response.setMessage(errorMessage.toString());
            return ResponseEntity.internalServerError().body(response);
        }

        CustomerResponse response = new CustomerResponse();
        response.setSuccess(true);
        response.setMessage("Customer inserito correttamente");

        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<CustomerResponse> modificaIndirizzoCustomer(String codiceFiscale, CustomerRequest customerRequest) {
        logger.info("Modifica dell'indirizzo un customer tramite codice fiscale {} con il nuovo indirizzo {}", codiceFiscale, customerRequest.getIndirizzo());
        try {

            Customer customer = customerRepository.findByCodiceFiscale(codiceFiscale);
            customer.setIndirizzo(customerRequest.getIndirizzo());
            customerRepository.save(customer);

        } catch (Exception e) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Errore durante la modifica del customer tramite codice fiscale: ");
            errorMessage.append(e.getMessage());
            logger.error(errorMessage.toString());
            CustomerResponse response = new CustomerResponse();
            response.setSuccess(false);
            response.setMessage(errorMessage.toString());
            return ResponseEntity.internalServerError().body(response);
        }
        CustomerResponse response = new CustomerResponse();
        response.setSuccess(true);
        response.setMessage("Indirizzo del customer modificato correttamente");

        return ResponseEntity.ok().body(response);
    }
}
