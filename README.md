# A Java EE REST API

## Clone projec

This web application was designed to add (standard or premier) bank clients to a bank 
Each client has a name, a type (premier or standard) and an initial balance.

## Cloning the project

git clone https://github.com/dragotaa/dragota-jee-rest-api.git

## Start the project

Prerequisites: you need to have Maven installed and setup on your machine

You can start the app using `mvn clean package tomee:run` in the main folder (dragota-jee-rest-api)
 
## Access the app

In order to access the start page of this web application please access the link bellow: 
http://localhost:8080 

Functionality:
The index page will redirect the user of the app towards the bankClient.jsf.
In this page, the user will be able to add new bank clients.
To add premier bank clients, the user needs to tick/mark the premier field on the page. 

## Access to premier clients

In order to see the list of premier clients in JSON format please access this link: 
http://localhost:8080/premierBankClients/ 

## License

Apache 2.0, see [LICENSE](LICENSE).
