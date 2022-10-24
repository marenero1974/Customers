package it.customers.h2db.springboot.controller;

import it.customers.h2db.springboot.dto.InserimentoCustomerRequest;
import it.customers.h2db.springboot.dto.InserimentoCustomerResponse;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerCareApi {
  @RequestMapping(
      value = "/customer-care/customer",
      produces = { "application/json" },
      method = RequestMethod.POST
  )
  ResponseEntity<InserimentoCustomerResponse> inserisciCustomer(@NotNull @RequestParam(value = "input", required = true) InserimentoCustomerRequest input

);
}