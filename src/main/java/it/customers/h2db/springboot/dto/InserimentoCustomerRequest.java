package it.customers.h2db.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;


@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-24T21:17:08.805186600+02:00[Europe/Rome]")
public class InserimentoCustomerRequest   {
  @JsonProperty("nome")
  private String nome;

  @JsonProperty("cognome")
  private String cognome;

  @JsonProperty("codiceFiscale")
  private String codiceFiscale;

  @JsonProperty("indirizzo")
  private String indirizzo;

  /**
   **/
  public InserimentoCustomerRequest nome(String nome) {
    this.nome = nome;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("nome")
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   **/
  public InserimentoCustomerRequest cognome(String cognome) {
    this.cognome = cognome;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("cognome")
  public String getCognome() {
    return cognome;
  }
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   **/
  public InserimentoCustomerRequest codiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("codiceFiscale")
  public String getCodiceFiscale() {
    return codiceFiscale;
  }
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   **/
  public InserimentoCustomerRequest indirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("indirizzo")
  public String getIndirizzo() {
    return indirizzo;
  }
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InserimentoCustomerRequest inserimentoCustomerRequest = (InserimentoCustomerRequest) o;
    return Objects.equals(nome, inserimentoCustomerRequest.nome) &&
        Objects.equals(cognome, inserimentoCustomerRequest.cognome) &&
        Objects.equals(codiceFiscale, inserimentoCustomerRequest.codiceFiscale) &&
        Objects.equals(indirizzo, inserimentoCustomerRequest.indirizzo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, cognome, codiceFiscale, indirizzo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InserimentoCustomerRequest {\n");
    
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    indirizzo: ").append(toIndentedString(indirizzo)).append("\n");
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

