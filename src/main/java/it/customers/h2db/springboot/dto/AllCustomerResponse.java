package it.customers.h2db.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Objects;


@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-26T20:48:47.723996500+02:00[Europe/Rome]")
public class AllCustomerResponse   {
  @JsonProperty("customers")
  private List<CustomerDTO> customers = null;

  /**
   **/
  public AllCustomerResponse customers(List<CustomerDTO> customers) {
    this.customers = customers;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("customers")
  public List<CustomerDTO> getCustomers() {
    return customers;
  }
  public void setCustomers(List<CustomerDTO> customers) {
    this.customers = customers;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AllCustomerResponse allCustomerResponse = (AllCustomerResponse) o;
    return Objects.equals(customers, allCustomerResponse.customers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AllCustomerResponse {\n");
    
    sb.append("    customers: ").append(toIndentedString(customers)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

