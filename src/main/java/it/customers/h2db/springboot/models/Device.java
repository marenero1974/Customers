package it.customers.h2db.springboot.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "device")
public class Device {

  public Device() {}

  public Device(UUID uuid, String stato, Customer customer) {
    this.uuid = uuid;
    this.stato = stato;
    this.customer = customer;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_GEN")
  @SequenceGenerator(name = "device_GEN", sequenceName = "device_SEQ")
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(unique = true)
  private UUID uuid;

  private String stato;

  @ManyToOne(cascade= CascadeType.ALL)
  @JoinColumn(name = "customer_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Customer customer;


  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getStato() {
    return stato;
  }

  public void setStato(String stato) {
    this.stato = stato;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Device device = (Device) o;
    return getId().equals(device.getId()) && getUuid().equals(device.getUuid())
        && getStato().equals(
        device.getStato()) && getCustomer().equals(device.getCustomer());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUuid(), getStato(), getCustomer());
  }

  @Override
  public String toString() {
    return "Device{" +
        "id=" + id +
        ", uuid=" + uuid +
        ", stato='" + stato + '\'' +
        ", customer=" + customer +
        '}';
  }
}
