<p> Please se following comments to get started</p>

<p>To generate a jar file, navigate to the project folder and type:</p>
<h1> mvn clean install</h1>
<p>This will run all the Unit tests, Performance test and Integration test and build a shaded jar file</p>
<br/>
<br/>


<p>rest-prime-service.jar localhost and port 8888</p>
Navigate to the folder where jar is resident mostly would be in target folder unless settings are different
<p>To run rest-prime-service</p>
<p>java -jar rest-prime-service.jar -XX:+PrintGCDateStamps -verbose:gc -XX:+PrintGCDetails -Xloggc:"./logs/gc.log"</p>

<h1>Running rest-prime-service</h1>
<a href='http://localhost:8888/primes/13'>http://localhost:8888/primes/13/</a>
</br>
<a href='http://localhost:8888/primeswithstream/13'>http://localhost:8888/primeswithstream/13/</a>
<br/>
<a href='http://localhost:8888/primeswithparallelstream/13'>http://localhost:8888/primeswithparallelstream/13/</a>
<br/>
<a href='http://localhost:8888/primeswithforkandjoin/13'>http://localhost:8888/primeswithforkandjoin/13/</a>
<br/>
<a href='http://localhost:8888/primeswithforkandjoinwithstream/13'>http://localhost:8888/primeswithforkandjoinwithstream/13/</a>
</br>

<p>Running Admin console</p>
<a href='http://localhost:8889'>http://localhost:8889</a>
<br/>
<p>This will give operation menu with following options
<li>Metrics</li>
<li>Ping</li>
<li>Threads</li>
<li>Healthcheck</li>
</p>

<h1>Some curl commands to try</h1>
<p>curl "http://localhost:8888/primes/13"</p>
<p>curl "http://localhost:8888/primeswithstream/13"</p>
<p>curl "http://localhost:8888/primeswithparallelstream/13"</p>
<p>curl "http://localhost:8888/primeswithforkandjoin/13"</p>
<p>curl "http://localhost:8888/primeswithforkandjoinwithstream/13"</p>








