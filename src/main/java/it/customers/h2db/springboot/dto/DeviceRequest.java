package it.customers.h2db.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;


@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-26T19:49:38.540810900+02:00[Europe/Rome]")
public class DeviceRequest {
  @JsonProperty("stato")
  private String stato;

  /**
   **/
  public DeviceRequest stato(String stato) {
    this.stato = stato;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("stato")
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
    DeviceRequest deviceRequest = (DeviceRequest) o;
    return Objects.equals(stato, deviceRequest.stato);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stato);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeviceRequest {\n");
    
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
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

