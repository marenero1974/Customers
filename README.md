# Customers
Customer care app:

impostare un settings.xml libero come ad esempio:

<?xml version="1.0" encoding="UTF-8"?>
<settings xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.1.0 http://maven.apache.org/xsd/settings-1.1.0.xsd" xmlns="http://maven.apache.org/SETTINGS/1.1.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
</settings>

L'applicazione usa db in memory h2

- Fare build con mvn clean package/install

- per avviare l'applicazione eseguire la classe SpringBootH2Application

Una volta avviata si puo verificare con il browser lo stato del database e dei dati contenuti
accedendo alla console di H2 tramite l'indirizzo: http://localhost:8080/h2-console
inserendo:
JDBC URL: jdbc:h2:mem:test
User Name: sa
Password: pass


- La prima api che deve essere eseguita è la creazione di un utente, con postman:
POST http://localhost:8080/customer-care/customer 
- body:   
{
  "nome" : "prova",
  "cognome" : "provacognome",
  "codiceFiscale" : "codfisc",
  "indirizzo" : "indirizzo",
  "devices" : [{
  "stato":"ACTIVE",
  "uuid":"c522a098-5478-11ed-bdc3-0242ac120004"
  }, {
  "stato":"ACTIVE",
  "uuid":"c522a098-5478-11ed-bdc3-0242ac120005"
  }]
}
una volta invocato questo endpoint si possono eseguire tutti gli altri:

per es. per verificare se un device è presente
GET http://localhost:8080/customer-care/device/c522a098-5478-11ed-bdc3-0242ac120004

modificare un device:
PATCH http://localhost:8080/customer-care/modifica/device/c522a098-5478-11ed-bdc3-0242ac120004
con body il nuovo stato del device:
{
"stato" : "INACTIVE"
}

per modificare l'indirizzo del customer:
PATCH http://localhost:8080/customer-care/modifica/customer/codfisca
con body il nuovo indirizzo:
{
"indirizzo" : "via roma 103"
}


per avere tutti gli utenti presenti nel db:
GET http://localhost:8080/customer-care/customers

per avere un utente tramite codice fiscale (inserito univocamente nel db):
GET http://localhost:8080/customer-care/customers/codfisc

infine per cancellare un device tramite uuid (colonna univoca nella tabella device)
DELETE http://localhost:8080/customer-care/delete/device/{uuid}    uuid per esempio: c522a098-5478-11ed-bdc3-0242ac120004












