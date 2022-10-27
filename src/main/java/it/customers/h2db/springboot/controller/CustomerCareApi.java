package it.customers.h2db.springboot.controller;

import it.customers.h2db.springboot.dto.AllCustomerResponse;
import it.customers.h2db.springboot.dto.CustomerDTO;
import it.customers.h2db.springboot.dto.CustomerRequest;
import it.customers.h2db.springboot.dto.CustomerResponse;
import it.customers.h2db.springboot.dto.DeviceRequest;
import it.customers.h2db.springboot.dto.InserimentoCustomerRequest;
import it.customers.h2db.springboot.dto.IsDevicePresent;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerCareApi {

  @RequestMapping(
      value = "/customer-care/delete/device/{uuid}",
      produces = { "application/json" },
      method = RequestMethod.DELETE
  )
  ResponseEntity<CustomerResponse> deleteDevice(@PathVariable("uuid") String uuid);

  @RequestMapping(
      value = "/customer-care/customers/{codiceFiscale}",
      produces = { "application/json" },
      method = RequestMethod.GET
  )
  ResponseEntity<CustomerDTO> findCustomerByCodiceFiscale(@PathVariable("codiceFiscale") String codiceFiscale);

  @RequestMapping(
      value = "/customer-care/customers",
      produces = { "application/json" },
      method = RequestMethod.GET
  )
  ResponseEntity<AllCustomerResponse> getAllCustomers();

  @RequestMapping(
      value = "/customer-care/device/{uuid}",
      produces = { "application/json" },
      method = RequestMethod.GET
  )
  ResponseEntity<IsDevicePresent> isDevicePresent(@PathVariable("uuid") String uuid);
  @RequestMapping(
      value = "/customer-care/customer",
      consumes = { "application/json" },
      produces = { "application/json" },
      method = RequestMethod.POST
  )
  ResponseEntity<CustomerResponse> inserisciCustomer(@NotNull @RequestBody(required = true) InserimentoCustomerRequest input);
  @RequestMapping(
      value = "/customer-care/modifica/customer/{codiceFiscale}",
      consumes = { "application/json" },
      produces = { "application/json" },
      method = RequestMethod.PATCH
  )
  ResponseEntity<CustomerResponse> modificaIndirizzoCustomer(@PathVariable("codiceFiscale") String codiceFiscale, @NotNull @RequestBody(required = true) CustomerRequest customerRequest);

  @RequestMapping(
      value = "/customer-care/modifica/device/{uuid}",
      produces = { "application/json" },
      method = RequestMethod.PATCH
  )
  ResponseEntity<CustomerResponse> modificaStatoDevice(@PathVariable("uuid") String uuid, @NotNull @RequestBody(required = true) DeviceRequest deviceRequest);
}