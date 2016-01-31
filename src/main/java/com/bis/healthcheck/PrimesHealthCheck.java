package com.bis.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


public class PrimesHealthCheck extends HealthCheck {

    private final String URL = "http://localhost:8888/primes/15";

    private Boolean getHealthCheck() {
        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.connectorProvider(new ApacheConnectorProvider());

        Client client = ClientBuilder.newBuilder().
                hostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .withConfig(clientConfig)
                .build();
        return (client.target(URL).request().get().getStatus()) == 200;
    }


    @Override
    protected Result check() throws Exception {
        return getHealthCheck() == true ? Result.healthy() : Result.unhealthy("Service has not responded positively");
    }
}
