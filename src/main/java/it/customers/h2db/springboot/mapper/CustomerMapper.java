package it.customers.h2db.springboot.mapper;

import it.customers.h2db.springboot.dto.CustomerDTO;
import it.customers.h2db.springboot.dto.DeviceDTO;
import it.customers.h2db.springboot.dto.InserimentoCustomerRequest;
import it.customers.h2db.springboot.models.Customer;
import it.customers.h2db.springboot.models.Device;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomerMapper {

  public static Customer mapToEntity(InserimentoCustomerRequest dto) {
    Customer customer = null;
    if (dto != null) {
      customer = new Customer();
      customer.setNome(dto.getNome());
      customer.setCognome(dto.getCognome());
      customer.setIndirizzo(dto.getIndirizzo());
      customer.setCodiceFiscale(dto.getCodiceFiscale());
      Customer finalCustomer = customer;
      List<Device> devices = dto.getDevices().stream()
          .map(p -> new Device(UUID.fromString(p.getUuid()), p.getStato(), finalCustomer))
          .collect(Collectors.toList());
      customer.setDeviceList(devices);
    }
    return customer;
  }

  public static List<CustomerDTO> mapToDto(List<Customer> customers) {
    List<CustomerDTO> customerDTOS = new ArrayList<>();
    if(customers != null && customers.size() > 0) {
      customerDTOS = customers.stream()
          .map(p -> {
            List<DeviceDTO> deviceDTOS = p.getDeviceList()
                .stream().map(d -> new DeviceDTO(d.getStato(), d.getUuid().toString()))
                .collect(Collectors.toList());
            return new CustomerDTO(p.getNome(),
              p.getCognome(),
              p.getCodiceFiscale(),
              p.getIndirizzo(), deviceDTOS);
          })
          .collect(Collectors.toList());
    }
    return customerDTOS;
  }
}
