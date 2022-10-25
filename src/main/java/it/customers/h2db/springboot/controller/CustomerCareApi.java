package it.customers.h2db.springboot.controller;
import it.customers.h2db.springboot.dto.InserimentoCustomerRequest;
import it.customers.h2db.springboot.dto.InserimentoCustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;
public interface CustomerCareApi {
  @RequestMapping(
      value = "/customer-care/customer",
      consumes = { "application/json" },
      produces = { "application/json" },
      method = RequestMethod.POST
  )
  ResponseEntity<InserimentoCustomerResponse> inserisciCustomer(@NotNull @RequestBody() InserimentoCustomerRequest input);
}