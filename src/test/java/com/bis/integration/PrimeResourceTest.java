package com.bis.integration;

import com.bis.config.PrimeServiceConfiguration;
import com.bis.resource.PrimeServiceRunner;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PrimeResourceTest {

    private static final Integer MAX_LIMIT_OVER = 1000000 + 1;

    private static final Integer TEST_ITEMS = 15;

    private static final Integer ZERO = 0;

    private static final Integer ONE = 0;

    private final List<Integer> INCLUSIVE_ITEM_LIST = Arrays.asList(2, 3, 5, 7, 11, 13);

    private final List<Integer> INCLUSIVE_BLANK_ITEM_LIST = Arrays.asList(new Integer[]{});

    private static final int BAD_REQUEST_CODE = 400;

    private static final int OK_CODE = 200;

    @ClassRule
    public static final DropwizardAppRule<PrimeServiceConfiguration> RULE =
            new DropwizardAppRule<>(PrimeServiceRunner.class, "configuration.yaml");

    private Client getClient() {
        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.connectorProvider(new ApacheConnectorProvider());

        return ClientBuilder.newBuilder().
                hostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .withConfig(clientConfig)
                .build();
    }

    private void checkResponse(final Response response, final List<Integer> expectedResuts) {
        assertEquals(OK_CODE, response.getStatus());

        final String json = response.readEntity(String.class);

        final List<Integer> primes = new Gson().fromJson(json,
                new TypeToken<ArrayList<Integer>>() {
                }.getType());
        assertEquals(expectedResuts, primes);
    }


    private void checkErrorResponse(final Response response, final int statusCode) {
        assertEquals(statusCode, response.getStatus());
    }

    @Test
    public void checkErrorTest() throws IOException {
        //400 Error
        checkErrorResponse(getClient().target("http://localhost:8888/primes/-1").request().get(), BAD_REQUEST_CODE);
        checkErrorResponse(getClient().target("http://localhost:8888/primeswithstream/-1").request().get(), BAD_REQUEST_CODE);
        checkErrorResponse(getClient().target("http://localhost:8888/primeswithparallelstream/-1").request().get(), BAD_REQUEST_CODE);
        checkErrorResponse(getClient().target("http://localhost:8888/primeswithforkandjoin/-1").request().get(), BAD_REQUEST_CODE);
        checkErrorResponse(getClient().target("http://localhost:8888/primeswithforkandjoinwithstream/-1").request().get(), BAD_REQUEST_CODE);

        //400 Error
        checkErrorResponse(getClient().target("http://localhost:8888/primes/" + MAX_LIMIT_OVER).request().get(), BAD_REQUEST_CODE);
        checkErrorResponse(getClient().target("http://localhost:8888/primeswithstream/" + MAX_LIMIT_OVER).request().get(), BAD_REQUEST_CODE);
        checkErrorResponse(getClient().target("http://localhost:8888/primeswithparallelstream/" + MAX_LIMIT_OVER).request().get(), BAD_REQUEST_CODE);
        checkErrorResponse(getClient().target("http://localhost:8888/primeswithforkandjoin/" + MAX_LIMIT_OVER).request().get(), BAD_REQUEST_CODE);
        checkErrorResponse(getClient().target("http://localhost:8888/primeswithforkandjoinwithstream/" + MAX_LIMIT_OVER).request().get(), BAD_REQUEST_CODE);
    }

    @Test
    public void integrationPrimesTest() throws IOException {
        checkResponse(getClient().target("http://localhost:8888/primes/" + TEST_ITEMS).request().get(), INCLUSIVE_ITEM_LIST);
        checkResponse(getClient().target("http://localhost:8888/primeswithstream/" + TEST_ITEMS).request().get(), INCLUSIVE_ITEM_LIST);
        checkResponse(getClient().target("http://localhost:8888/primeswithparallelstream/" + TEST_ITEMS).request().get(), INCLUSIVE_ITEM_LIST);
        checkResponse(getClient().target("http://localhost:8888/primeswithforkandjoin/" + TEST_ITEMS).request().get(), INCLUSIVE_ITEM_LIST);
        checkResponse(getClient().target("http://localhost:8888/primeswithforkandjoinwithstream/" + TEST_ITEMS).request().get(), INCLUSIVE_ITEM_LIST);

        checkResponse(getClient().target("http://localhost:8888/primes/" + ZERO).request().get(), INCLUSIVE_BLANK_ITEM_LIST);
        checkResponse(getClient().target("http://localhost:8888/primeswithstream/" + ZERO).request().get(), INCLUSIVE_BLANK_ITEM_LIST);
        checkResponse(getClient().target("http://localhost:8888/primeswithparallelstream/" + ZERO).request().get(), INCLUSIVE_BLANK_ITEM_LIST);
        checkResponse(getClient().target("http://localhost:8888/primeswithforkandjoin/" + ZERO).request().get(), INCLUSIVE_BLANK_ITEM_LIST);
        checkResponse(getClient().target("http://localhost:8888/primeswithforkandjoinwithstream/" + ZERO).request().get(), INCLUSIVE_BLANK_ITEM_LIST);

        checkResponse(getClient().target("http://localhost:8888/primes/" + ONE).request().get(), INCLUSIVE_BLANK_ITEM_LIST);
        checkResponse(getClient().target("http://localhost:8888/primeswithstream/" + ONE).request().get(), INCLUSIVE_BLANK_ITEM_LIST);
        checkResponse(getClient().target("http://localhost:8888/primeswithparallelstream/" + ONE).request().get(), INCLUSIVE_BLANK_ITEM_LIST);
        checkResponse(getClient().target("http://localhost:8888/primeswithforkandjoin/" + ONE).request().get(), INCLUSIVE_BLANK_ITEM_LIST);
        checkResponse(getClient().target("http://localhost:8888/primeswithforkandjoinwithstream/" + ONE).request().get(), INCLUSIVE_BLANK_ITEM_LIST);
    }
}
