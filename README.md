## MySql setup
 * Create database spring_batch_test 
 * then execute schema-mysql.sql
 *  and ddl.sql 

## How to run
 * mvn clean install
 * java -jar target/stock-batch-example-0.0.1-SNAPSHOT.jar jobs/job.xml stockJob

##Yahoo finance stuff
 * stock symbol lookup http://finance.yahoo.com/lookup
 * endpoint http://finance.yahoo.com/webservice/v1/symbols/MSFT/quote?format=json
