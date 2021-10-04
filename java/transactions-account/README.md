## 1 - Creating a user with low permissions :
Connect to your local or even remote MySQL server and create a user that will have
enough permissions to create a database, create tables, create indexes, select/insert data, ...
No need to create an empty database since Liquibase will do it for us, we just need a valid user.

## 2 - Define the required environment variables :
* `MYSQL_TRANSACTIONS_ACCOUNT_HOST`, ex : localhost
* `MYSQL_TRANSACTIONS_ACCOUNT_PORT`, ex : 3306
* `MYSQL_TRANSACTIONS_ACCOUNT_DATABASE`, ex : transactions_account_innodb
* `MYSQL_TRANSACTIONS_ACCOUNT_USERNAME`, ex : local-user
* `MYSQL_TRANSACTIONS_ACCOUNT_PASSWORD`, ex : @password123!

## 3 - Create and populate the database :
From the Maven project transactions-account, using the terminal :
* `mvn clean install`
* `mvn liquibase:update`
* `mvn spring-boot:run`

## 4 - Test REST endpoints
Install Postman and import the collection (src/main/resources/postman_collection.json).
Run the scenario you want (feel free to modify then if needed). You should have everything
you need to paginate, filter and sort the transactions. You can also take a look at the OpenAPI page (http://localhost:8080/swagger-ui/index.html).