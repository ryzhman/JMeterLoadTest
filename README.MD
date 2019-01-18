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

3. Generation of prettyfied HTML report based on JMX result file <br>
JMX file by default contains plain text with response results. 
To convert it something more reasonable you can generate HTML by basic JMeter tools. <br>
In order to do it you need to have JMeter environment variable set up locally (should be done manually) and have JMeter itself installed. <br>
To get the HTML with report: <br>
a. run Step 2 from this manual <br>
b. run this command in your command prompt open in the project directory: <br>
`jmeter -g src/test/results/itemAppResults.jmx -o src/test/results/htmlReport/` <br>
it will use the JMX file generated before and create a report in `src/test/results/htmlReport/` folder

`






