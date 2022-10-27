package it.customers.h2db.springboot.controller;

import it.customers.h2db.springboot.dto.AllCustomerResponse;
import it.customers.h2db.springboot.dto.CustomerDTO;
import it.customers.h2db.springboot.dto.CustomerRequest;
import it.customers.h2db.springboot.dto.CustomerResponse;
import it.customers.h2db.springboot.dto.DeviceRequest;
import it.customers.h2db.springboot.dto.InserimentoCustomerRequest;
import it.customers.h2db.springboot.dto.IsDevicePresent;
import it.customers.h2db.springboot.mapper.CustomerMapper;
import it.customers.h2db.springboot.models.Customer;
import it.customers.h2db.springboot.models.Device;
import it.customers.h2db.springboot.repositry.CustomerRepository;
import it.customers.h2db.springboot.repositry.DeviceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<CustomerDTO> findCustomerByCodiceFiscale(String codiceFiscale) {
    logger.info("findCustomerByCodiceFiscale  --- START ---- ");
    Customer customer = null;
    try {
      customer = customerRepository.findByCodiceFiscale(codiceFiscale);
      if(customer == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return logAndBuildResponse("Impossibile ottenere l'utente tramite il codice fiscale", e, ResponseEntity.class);
    }

    return ResponseEntity.ok(CustomerMapper.mapToDto(customer));
  }

  @Override
  public ResponseEntity<AllCustomerResponse> getAllCustomers() {
    logger.info("Fetch di tutti gli utenti");
    List<Customer> customers = new ArrayList<>();
    try {
      customerRepository.findAll().forEach(customers::add);
      if(customers.size() == 0) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return logAndBuildResponse("Impossibile ottenere la lista degli utenti", e, ResponseEntity.class);
    }

    AllCustomerResponse all = new AllCustomerResponse();
    List<CustomerDTO> customerDTOS = CustomerMapper.mapCustomersToDto(customers);
    all.setCustomers(customerDTOS);

    return ResponseEntity.ok(all);
  }

  @Override
  public ResponseEntity<IsDevicePresent> isDevicePresent(String uuid) {
    IsDevicePresent isDevicePresent = new IsDevicePresent();
    try {
      Device device = deviceRepository.findDeviceByUuid(UUID.fromString(uuid));
      if(device == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        isDevicePresent.setIsPresent(true);
      }
    } catch (Exception e) {
      return logAndBuildResponse("Impossibile ottenere il device tramite uuid fornito", e, ResponseEntity.class);
    }
    return ResponseEntity.ok(isDevicePresent);
  }

  @Override
  public ResponseEntity<CustomerResponse> inserisciCustomer(InserimentoCustomerRequest input) {
    logger.info("Inserimento di un customer {}", input);
    Customer customer = CustomerMapper.mapToEntity(input);

    if (customer.getDeviceList().size() > 2) {
      String errorMessage = "Il numero di device per un utente non deve essere maggiore di 2";
      CustomerResponse response = buildResponse(false, errorMessage);
      logger.error(errorMessage);
      return ResponseEntity.internalServerError().body(response);
    }

    try {
      customerRepository.save(customer);
      logger.info("Inserimento di un customer completato.");
    } catch (Exception e) {
      return logAndBuildResponse("Errore durante il salvataggio del customer:", e, ResponseEntity.class);
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
      if(customer == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      customer.setIndirizzo(customerRequest.getIndirizzo());
      customerRepository.save(customer);
    } catch (Exception e) {
      return logAndBuildResponse(
          "Errore durante la modifica del customer tramite codice fiscale: ", e, ResponseEntity.class);
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
          "Errore durante la modifica dello stato del device tramite uuid: ", e, ResponseEntity.class);
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

  private static <T extends ResponseEntity> T logAndBuildResponse(String str, Exception e, Class<T> type) {
    StringBuilder errorMessage = new StringBuilder();
    errorMessage.append(str);
    errorMessage.append(e.getMessage());
    logger.error(errorMessage.toString());
    CustomerResponse response = buildResponse(false,
        errorMessage.toString());
    return type.cast(ResponseEntity.internalServerError().body(response));
  }
}
