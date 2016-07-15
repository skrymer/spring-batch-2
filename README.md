## MySql setup
 * Create database spring_batch_test 
 * then execute schema-mysql.sql
 *  and ddl.sql 

## How to run
 * mvn clean install
 * then mvn jetty:run
 * then http:localhost:8080/index

##Yahoo finance stuff
 * stock symbol lookup http://finance.yahoo.com/lookup
 * endpoint http://finance.yahoo.com/webservice/v1/symbols/MSFT/quote?format=json
