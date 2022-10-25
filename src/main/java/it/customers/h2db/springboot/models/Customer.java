package it.customers.h2db.springboot.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;

@Table(name = "customer")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_GEN")
    @SequenceGenerator(name = "customer_GEN", sequenceName = "customer_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String indirizzo;

    @OneToMany(mappedBy="customer", cascade= CascadeType.ALL)
    private List<Device> deviceList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return getId().equals(customer.getId()) && getNome().equals(customer.getNome())
            && getCognome().equals(customer.getCognome()) && getCodiceFiscale().equals(
            customer.getCodiceFiscale()) && getIndirizzo().equals(customer.getIndirizzo())
            && getDeviceList().equals(customer.getDeviceList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCognome(), getCodiceFiscale(), getIndirizzo(),
            getDeviceList());
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", cognome='" + cognome + '\'' +
            ", codiceFiscale='" + codiceFiscale + '\'' +
            ", indirizzo='" + indirizzo + '\'' +
            ", deviceList=" + deviceList +
            '}';
    }
}
