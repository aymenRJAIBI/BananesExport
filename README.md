# BananesExport
```bash
 Clone the project
```
## Configuration of DataBase
 ```bash
Update application.properties with a new DataBase
spring.datasource.url=jdbc:mysql://localhost:3306/exportBananes_DB
Maven clean install
```
## Test API project using Postman Client
Request URL: http://localhost:8080/recipients/

```bash
HTTP Method: POST
Request Body:
{
        "name": "Hugo  Renault",
        "address": "975 Avenue de paris",
        "city": "Montpellier",
        "country": "France"
    }
```
Request URL: http://localhost:8080/recipients/

```bash
HTTP Method: GET
get all recipients

{
        "id": 52,
        "name": "Hugo  Renault",
        "address": "975 Avenue de paris",
        "city": "Montpellier",
        "country": "France"
    },
    {
        "id": 53,
        "name": "Gabriel Michel",
        "address": "654 Rue de bordeaux",
        "city": "Toulon",
        "country": "France"
    },
    {
        "id": 102,
        "name": "gabriel martinelli",
        "address": "777 Rue de arsenal",
        "city": "Toulon",
        "country": "France"
    }
 ```
Request URL: http://localhost:8080/recipients/1
```bash
HTTP Method: DELETE
Delete recipient by ID

Response :
recipient successfully deleted!
```
Request URL: http://localhost:8080/recipients/52

```bash
HTTP Method: PUT
Update  recipient by ID.
Request Body:

{
        "name": "Hugo  Renault",
        "address": "975 Avenue de paris",
        "city": "Montpellier",
        "country": "France"
    }
Response :
recipient successfully updated!
```
Request URL: http://localhost:8080/orders/

```bash
HTTP Method: POST
Request Body:
{
       "deliveryDate": "2023-07-29",
        "quantity": 200,
        "idRecipient": 102    
    }
Response :
order successfully saved!
```
```
Request URL: http://localhost:8080/orders/

```bash
HTTP Method: GET
get all orders

 {
        "id": 52,
        "deliveryDate": "2023-07-29",
        "quantity": 200,
        "idRecipient": 52
    },
    {
        "id": 53,
        "deliveryDate": "2023-07-29",
        "quantity": 200,
        "idRecipient": 102
    },
    {
        "id": 54,
        "deliveryDate": "2023-07-29",
        "quantity": 150,
        "idRecipient": 102
    },
  
 ```
Request URL: http://localhost:8080/orders/53
```bash
HTTP Method: DELETE
Delete recipient by ID

Response :
order successfully deleted!
```
Request URL: http://localhost:8080/orders/52

```bash
HTTP Method: PUT
Update  recipient by ID.
Request Body:
  {
    "deliveryDate": "2023-07-25",
    "quantity": 50,
    "idRecipient": 52
  }
Response :
Order successfully updated!
```