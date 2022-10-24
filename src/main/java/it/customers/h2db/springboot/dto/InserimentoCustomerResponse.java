package it.customers.h2db.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;


@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-24T21:17:08.805186600+02:00[Europe/Rome]")
public class InserimentoCustomerResponse   {
  @JsonProperty("success")
  private Boolean success;

  /**
   **/
  public InserimentoCustomerResponse success(Boolean success) {
    this.success = success;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("success")
  public Boolean getSuccess() {
    return success;
  }
  public void setSuccess(Boolean success) {
    this.success = success;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InserimentoCustomerResponse inserimentoCustomerResponse = (InserimentoCustomerResponse) o;
    return Objects.equals(success, inserimentoCustomerResponse.success);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InserimentoCustomerResponse {\n");
    
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
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

