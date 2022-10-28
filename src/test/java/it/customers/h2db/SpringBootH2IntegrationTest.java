package it.customers.h2db;

import static org.assertj.core.api.Assertions.assertThat;

import it.customers.h2db.springboot.SpringBootH2Application;
import it.customers.h2db.springboot.models.Customer;
import it.customers.h2db.springboot.models.Device;
import it.customers.h2db.springboot.repositry.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootH2Application.class)
public class SpringBootH2IntegrationTest {

    private static final Customer AN_EXPECTED_CUSTOMER = buildCountry();

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void givenInitData_whenApplicationStarts_thenDataIsLoaded() {

        customerRepository.save(AN_EXPECTED_CUSTOMER);

        Iterable<Customer> users = customerRepository.findAll();

        assertThat(users)
          .hasSize(1);
        assertThat(users.iterator().next().getNome().equals("Canada"));
    }

    private static Customer buildCountry() {
        Customer c = new Customer();
        c.setId(1L);
        c.setNome("Canada");
        c.setCognome("Cognome");
        c.setIndirizzo("indirizzo");
        c.setCodiceFiscale("codfisc");
        List<Device> deviceList = new ArrayList<>();
        c.setDeviceList(deviceList);
        return c;
    }

}
