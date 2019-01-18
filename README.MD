<h1>Sample onload test created with JMeter</h1>

<h2>Content</h2>

<h3>Web service to test</h3>
Web service is generated with a use of Spring Boot with two layers: Controller and dummy Service. <br>

API consists of several endpoints representing the GET, POST and DELETE HTTP requests.

<h4>Endpoints</h4>
GET localhost:8080/item/{itemId} -> returns JSON object with Item <br>
POST localhost:8080/item/{itemId} + body with updated Item -> returns updated Item<br>
DELETE localhost:8080/item/{itemId} -> returns boolean<br>

<h3>Sample JMeter test configuration</h3>
1. Java running the JMeter test plan created via JMeter GUI. <br>
Responsible class: `com.paymentus.jmxRunner.LoadTestItemApp` <br>
JMX file: `testPlans/Item_app_sample_test_plan.jmx`<br>
Result output file: `src/test/results/itemAppResults.jmx`

2. JMeter test plan created via plain Java code <br>
Responsible class: `com.paymentus.javaRunner.LoadTestOfItemAppJavaBased` <br>
Result output file: `src/test/results/javaGeneratedResults.csv` <br>



