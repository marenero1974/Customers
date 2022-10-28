package it.customers.h2db;


import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.customers.h2db.springboot.SpringBootH2Application;
import it.customers.h2db.springboot.controller.CustomerCareApiController;
import it.customers.h2db.springboot.dto.CustomerRequest;
import it.customers.h2db.springboot.dto.DeviceDTO;
import it.customers.h2db.springboot.dto.DeviceRequest;
import it.customers.h2db.springboot.dto.InserimentoCustomerRequest;
import it.customers.h2db.springboot.models.Customer;
import it.customers.h2db.springboot.models.Device;
import it.customers.h2db.springboot.repositry.CustomerRepository;
import it.customers.h2db.springboot.repositry.DeviceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = SpringBootH2Application.class)
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(Lifecycle.PER_CLASS)
public class CustomerCareApiControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  CustomerCareApiController toTest;

  @MockBean
  DeviceRepository deviceRepository;


  @BeforeEach
  void setUp() {

    //popola db per i test
    if(customerRepository.findByCodiceFiscale("codfisc") == null) {
      Customer customer = new Customer();
      customer.setNome("nome");
      customer.setCognome("cognome");
      customer.setCodiceFiscale("codfisc");
      customer.setIndirizzo("via delle palme");
      List<Device> devices = new ArrayList<>();
      Device device = new Device(UUID.fromString("c522a098-5478-11ed-bdc3-0242ac120004"), "ACTIVE", customer);
      devices.add(device);
      customer.setDeviceList(devices);
      customerRepository.save(customer);
    }
  }

  @Test
  public void testfindCustomerByCodiceFiscaleApi() throws Exception {
    // When
    mvc.perform(get("/customer-care/customers/codfisc"))
        .andExpect(status().isOk());
  }

  @Test
  public void test_getAllCustomersApi() throws Exception {
    mvc.perform(get("/customer-care/customers"))
        .andExpect(status().isOk());
  }

  @Test
  public void test_InserisciCustomerApi() throws Exception {
    InserimentoCustomerRequest request = new InserimentoCustomerRequest();
    request.setNome("Canada");
    List<DeviceDTO> deviceList = new ArrayList<>();
    DeviceDTO d = new DeviceDTO("ACTIVE", "c522a098-5478-11ed-bdc3-0242ac120005");
    deviceList.add(d);
    request.setDevices(deviceList);
    request.setCodiceFiscale("codice1");
    mvc.perform(post("/customer-care/customer")
        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());
  }

  @Test
  public void test_modificaIndirizzoCustomerApi() throws Exception {
    CustomerRequest customerRequest = new CustomerRequest();
    customerRequest.setIndirizzo("via delle palme 12");
    mvc.perform(patch("/customer-care/modifica/customer/codfisc")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customerRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value("true"));;
  }


  // unit test
  @Test
  public void test_deleteDevice() {
    toTest.deleteDevice("c522a098-5478-11ed-bdc3-0242ac120004");
    verify(deviceRepository, Mockito.times(1)).deleteDeviceByUuid(ArgumentMatchers.any());
  }

  @Test
  public void test_deleteDevice_ko() {
    toTest.deleteDevice("not valid");
    verify(deviceRepository, Mockito.times(0)).deleteDeviceByUuid(ArgumentMatchers.any());
  }

  @Test
  public void test_findCustomerByCodiceFiscale() {
    toTest.findCustomerByCodiceFiscale("codfisc");
  }

  @Test
  public void test_getAllCustomers() {
    toTest.getAllCustomers();
  }

  @Test
  public void test_isDevicePresent() {
    toTest.isDevicePresent("c522a098-5478-11ed-bdc3-0242ac120004");
    verify(deviceRepository, Mockito.times(1)).findDeviceByUuid(ArgumentMatchers.any());
  }

  @Test
  public void test_inserisciCustomer() {
    InserimentoCustomerRequest request = new InserimentoCustomerRequest();
    request.setNome("nome");
    request.setCognome("cognome");
    request.setIndirizzo("indirizzo");
    request.setCodiceFiscale("codicefiscale");
    List<DeviceDTO> devicesDTo = new ArrayList<>();
    DeviceDTO device = new DeviceDTO("ACTIVE", "c522a098-5478-11ed-bdc3-0242ac120004");
    devicesDTo.add(device);
    request.setDevices(devicesDTo);
    toTest.inserisciCustomer(request);
  }

  @Test
  public void test_modificaIndirizzoCustomer() {
    CustomerRequest request = new CustomerRequest();
    request.setIndirizzo("prova");
    toTest.modificaIndirizzoCustomer("codfisc", request);
  }

  @Test
  public void test_modificaStatoDeviceNotFound() {
    DeviceRequest request = new DeviceRequest();
    request.setStato("INACTIVE");
    toTest.modificaStatoDevice("bbbbbbbb-5478-11ed-bdc3-0242ac120009", request);
    verify(deviceRepository, Mockito.times(1)).findDeviceByUuid(ArgumentMatchers.any());
    verify(deviceRepository, Mockito.times(0)).save(ArgumentMatchers.any());
  }







}
