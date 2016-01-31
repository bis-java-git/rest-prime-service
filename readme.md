<p> Please read following comments to get started</p>

<p>To generate a jar file, navigate to the project folder and type:</p>
<h1>mvn clean install</h1>
<p>This will run all the Unit tests, Performance test and Integration test and build a shaded jar file</p>

<p>To run rest-prime-service.jar</p>
<p>Navigate to the folder where jar is resident mostly would be in target folder unless settings are different</p>
<h1>java -jar rest-prime-service.jar -XX:+PrintGCDateStamps -verbose:gc -XX:+PrintGCDetails -Xloggc:"./logs/gc.log"</h1>

<p>Checking prime rest service</p>
<a href='http://localhost:8888/primes/15'>http://localhost:8888/primes/15/</a>
</br>
<a href='http://localhost:8888/primeswithstream/15'>http://localhost:8888/primeswithstream/15/</a>
<br/>
<a href='http://localhost:8888/primeswithparallelstream/15'>http://localhost:8888/primeswithparallelstream/15/</a>
<br/>
<a href='http://localhost:8888/primeswithforkandjoin/15'>http://localhost:8888/primeswithforkandjoin/15/</a>
<br/>
<a href='http://localhost:8888/primeswithforkandjoinwithstream/15'>http://localhost:8888/primeswithforkandjoinwithstream/15/</a>
</br>

<p>Checking prime rest service health check</p>
<a href='http://localhost:8889'>http://localhost:8889</a>
<br/>
<p>This will give operation menu with following options
<li>Metrics</li>
<li>Ping</li>
<li>Threads</li>
<li>Healthcheck</li>
</p>

<h1>Some curl commands to try</h1>
<p>curl "http://localhost:8888/primes/15"</p>
<p>curl "http://localhost:8888/primeswithstream/15"</p>
<p>curl "http://localhost:8888/primeswithparallelstream/15"</p>
<p>curl "http://localhost:8888/primeswithforkandjoin/15"</p>
<p>curl "http://localhost:8888/primeswithforkandjoinwithstream/15"</p>








