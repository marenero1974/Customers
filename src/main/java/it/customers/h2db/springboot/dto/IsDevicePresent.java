package it.customers.h2db.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;


@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-27T21:23:39.481798700+02:00[Europe/Rome]")
public class IsDevicePresent {
  @JsonProperty("isPresent")
  private Boolean isPresent;

  /**
   **/
  public IsDevicePresent isPresent(Boolean isPresent) {
    this.isPresent = isPresent;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("isPresent")
  public Boolean getIsPresent() {
    return isPresent;
  }
  public void setIsPresent(Boolean isPresent) {
    this.isPresent = isPresent;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IsDevicePresent isDevicePresent = (IsDevicePresent) o;
    return Objects.equals(isPresent, isDevicePresent.isPresent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isPresent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IsDevicePresent {\n");
    
    sb.append("    isPresent: ").append(toIndentedString(isPresent)).append("\n");
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

