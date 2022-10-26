package it.customers.h2db.springboot.controller;

import it.customers.h2db.springboot.dto.CustomerRequest;
import it.customers.h2db.springboot.dto.CustomerResponse;
import it.customers.h2db.springboot.dto.DeviceRequest;
import it.customers.h2db.springboot.dto.InserimentoCustomerRequest;
import it.customers.h2db.springboot.mapper.CustomerMapper;
import it.customers.h2db.springboot.models.Customer;
import it.customers.h2db.springboot.models.Device;
import it.customers.h2db.springboot.repositry.CustomerRepository;
import it.customers.h2db.springboot.repositry.DeviceRepository;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  @Autowired
  DeviceRepository deviceRepository;

  private static Logger logger = LoggerFactory.getLogger(CustomerCareApiController.class);


  @Override
  public ResponseEntity<CustomerResponse> inserisciCustomer(InserimentoCustomerRequest input) {
    logger.info("Inserimento di un customer {}", input);
    Customer customer = CustomerMapper.mapToEntity(input);

    if (customer.getDeviceList().size() > 2) {
      CustomerResponse response = buildResponse(false,
          "Il numero di device per un utente non deve essere maggiore di 2");
      logger.error("Il numero di device per un utente non deve essere maggiore di 2");
      return ResponseEntity.internalServerError().body(response);
    }

    try {
      customerRepository.save(customer);
      logger.info("Inserimento di un customer completato.");
    } catch (Exception e) {
      return logAndBuildResponse("Errore durante il salvataggio del customer:", e);
    }

    CustomerResponse response = buildResponse(true,
        "Customer inserito correttamente");

    return ResponseEntity.ok().body(response);
  }

  @Override
  public ResponseEntity<CustomerResponse> modificaIndirizzoCustomer(String codiceFiscale,
      CustomerRequest customerRequest) {
    logger.info(
        "Modifica dell'indirizzo un customer tramite codice fiscale {} con il nuovo indirizzo {}",
        codiceFiscale, customerRequest.getIndirizzo());

    try {
      Customer customer = customerRepository.findByCodiceFiscale(codiceFiscale);
      customer.setIndirizzo(customerRequest.getIndirizzo());
      customerRepository.save(customer);
    } catch (Exception e) {
      return logAndBuildResponse(
          "Errore durante la modifica del customer tramite codice fiscale: ", e);
    }
    CustomerResponse response = buildResponse(true,
        "Indirizzo del customer modificato correttamente");

    return ResponseEntity.ok().body(response);
  }

  @Override
  public ResponseEntity<CustomerResponse> modificaStatoDevice(String uuid, DeviceRequest deviceRequest) {
    logger.info(
        "Modifica dello stato di un device tramite codice uuid: {} con il nuovo stato: {}",
        uuid, deviceRequest.getStato());
    try {
      Device device = deviceRepository.findDeviceByUuid(UUID.fromString(uuid));
      device.setStato(deviceRequest.getStato());
      deviceRepository.save(device);
    } catch (Exception e) {
      return logAndBuildResponse(
          "Errore durante la modifica dello stato del device tramite uuid: ", e);
    }
    CustomerResponse response = buildResponse(true,
        "Stato del device modificato correttamente");

    return ResponseEntity.ok().body(response);
  }

  private static CustomerResponse buildResponse(boolean success, String message) {
    CustomerResponse response = new CustomerResponse();
    response.setSuccess(success);
    response.setMessage(message);
    return response;
  }

  private static ResponseEntity<CustomerResponse> logAndBuildResponse(String str, Exception e) {
    StringBuilder errorMessage = new StringBuilder();
    errorMessage.append(str);
    errorMessage.append(e.getMessage());
    logger.error(errorMessage.toString());
    CustomerResponse response = buildResponse(false,
        errorMessage.toString());
    return ResponseEntity.internalServerError().body(response);
  }
}
