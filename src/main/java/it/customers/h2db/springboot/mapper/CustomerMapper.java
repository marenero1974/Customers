package it.customers.h2db.springboot.mapper;

import it.customers.h2db.springboot.dto.InserimentoCustomerRequest;
import it.customers.h2db.springboot.models.Customer;
import it.customers.h2db.springboot.models.Device;
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

}
