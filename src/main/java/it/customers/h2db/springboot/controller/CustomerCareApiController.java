package it.customers.h2db.springboot.controller;

import it.customers.h2db.springboot.dto.InserimentoCustomerRequest;
import it.customers.h2db.springboot.dto.InserimentoCustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-24T21:17:08.805186600+02:00[Europe/Rome]")
@Controller
@RequestMapping("${openapi.customerCareApplication.base-path:}")
public class CustomerCareApiController implements CustomerCareApi {

    @Override
    public ResponseEntity<InserimentoCustomerResponse> inserisciCustomer(
        InserimentoCustomerRequest input) {
        return null;
    }
}
