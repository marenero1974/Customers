package it.customers.h2db.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;


@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-25T17:09:19.005077800+02:00[Europe/Rome]")
public class DeviceDTO {
  @JsonProperty("stato")
  private String stato;

  @JsonProperty("uuid")
  private String uuid;

  /**
   **/
  public DeviceDTO stato(String stato) {
    this.stato = stato;
    return this;
  }

  public DeviceDTO(String stato, String uuid) {
    this.stato = stato;
    this.uuid = uuid;
  }

  @ApiModelProperty(value = "")
  @JsonProperty("stato")
  public String getStato() {
    return stato;
  }
  public void setStato(String stato) {
    this.stato = stato;
  }

  /**
   **/
  public DeviceDTO uuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("uuid")
  public String getUuid() {
    return uuid;
  }
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeviceDTO deviceDTO = (DeviceDTO) o;
    return Objects.equals(stato, deviceDTO.stato) &&
        Objects.equals(uuid, deviceDTO.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stato, uuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeviceDTO {\n");
    
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
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

