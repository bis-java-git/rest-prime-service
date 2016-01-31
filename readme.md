<p> readme.md file may help you to gate started</p>

<h1>mvn clean install</h1>
This will run all the Unit tests, Performance test and Integration test and build a shaded jar file
<br/>
<br/>


<h1>rest-prime-service.jar localhost and port 8888</h1>
Navigate to the folder where jar is resident mostly would be in target folder unless settings are different

java -jar rest-prime-service.jar -XX:+PrintGCDateStamps -verbose:gc -XX:+PrintGCDetails -Xloggc:"./logs/gc.log"

<h1>Running rest-prime-service</h1>
http://localhost:8888/primes/13
http://localhost:8888/primeswithstream/13
http://localhost:8888/primeswithparallelstream/13
http://localhost:8888/primeswithforkandjoin/13
http://localhost:8888/primeswithforkandjoinwithstream/13


Running Admin console  http://localhost:8889/
This will give operation menu with following options
Metrics
Ping
Threads
Healthcheck


<h1>Some curl commands to try</h1>
curl "http://localhost:8888/primes/13"
curl "http://localhost:8888/primeswithstream/13"
curl "http://localhost:8888/primeswithparallelstream/13"
curl "http://localhost:8888/primeswithforkandjoin/13"
curl "http://localhost:8888/primeswithforkandjoinwithstream/13"








