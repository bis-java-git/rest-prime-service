package com.bis.resource;

import com.bis.exception.InvalidNumberException;
import com.bis.service.PrimeNumberService;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Path(value = "/")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class PrimeResource {

    private static final Logger LOGGER = LoggerFactory.getLogger("com.bis.resource.PrimeResource");

    private final PrimeNumberService primeNumberService;

    private static final Integer MAX_LIMIT = 1000000;

    public PrimeResource(final PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
    }

    private void validate(final Integer upperLimit) throws InvalidNumberException {
        if (upperLimit < 0 || upperLimit > MAX_LIMIT) {
            LOGGER.error("Bad request invaild number {} range is 0 to {}", upperLimit, MAX_LIMIT);
            throw new InvalidNumberException("Bad request invaild number range 0 to " + MAX_LIMIT);
        }
    }



    @GET
    @Path(value = "primes/{upperNumber}")
    @Timed
    public List<Integer> getPrimes(@PathParam("upperNumber") final Integer upperLimit) throws InvalidNumberException {
        LOGGER.debug("getPrimes {})", upperLimit);
        validate(upperLimit);
        return primeNumberService.getPrimeNumbers(upperLimit);
    }

    @GET
    @Path(value = "primeswithstream/{upperNumber}")
    @Timed
    public List<Integer> getPrimesUsingStream(@PathParam("upperNumber") final Integer upperLimit) throws InvalidNumberException {
        LOGGER.debug("primeswithstream {})", upperLimit);
        validate(upperLimit);
        return primeNumberService.getPrimeNumbersWithStream(upperLimit);
    }

    @GET
    @Path(value = "primeswithparallelstream/{upperNumber}")
    @Timed
    public List<Integer> getPrimesUsingParallelStream(@PathParam("upperNumber") final Integer upperLimit) throws InvalidNumberException {
        LOGGER.debug("getPrimesUsingParallelStream {})", upperLimit);
        validate(upperLimit);
        return primeNumberService.getPrimeNumbersWithParallelStream(upperLimit);
    }

    @GET
    @Path(value = "primeswithforkandjoinwithstream/{upperNumber}")
    @Timed
    public List<Integer> getPrimesUsingForkAndJoinWithStream(@PathParam("upperNumber") final Integer upperLimit) throws InvalidNumberException {
        LOGGER.debug("getPrimesUsingForkAndJoinWithStream {})", upperLimit);
        validate(upperLimit);
        try {
            return primeNumberService.getPrimeNumberUsingForkAndPoolWithStream(upperLimit);
        } catch (ExecutionException | InterruptedException exc) {
            throw new InternalServerErrorException("Internal resource busy");
        }
    }


    @GET
    @Path(value = "primeswithforkandjoin/{upperNumber}")
    @Timed
    public List<Integer> getPrimesUsingForkAndJoin(@PathParam("upperNumber") int upperLimit) throws InvalidNumberException {
        LOGGER.debug("getPrimesUsingForkAndJoin {})", upperLimit);
        validate(upperLimit);
        try {
            return primeNumberService.getPrimeNumberUsingForkAndPool(upperLimit);
        } catch (ExecutionException | InterruptedException exc) {
            throw new InternalServerErrorException("Internal resource busy");
        }
    }

    @GET
    @Path(value = "help")
    @Produces(MediaType.TEXT_HTML)
    @Timed
    public String help(@PathParam("upperNumber") final Integer upperLimit) throws InvalidNumberException {
        LOGGER.debug("help");
        final StringBuffer htmlBuilder = new StringBuffer(1000);
        htmlBuilder.append("<html>").append("\n").append("<body>").append("<h1>Rest prime number service</h1>")
                .append("\n").append("<br/>").append("<br/>").append("\n").append("<a href='http://localhost:8888/primes/15'>http://localhost:8888/primes/15</a>")
                .append("\n").append("<br/>").append("<br/>").append("<a href='http://localhost:8888/primeswithstream/15'>http://localhost:8888/primeswithstream/15</a>")
                .append("\n").append("<br/>").append("<br/>").append("<a href='http://localhost:8888/primeswithparallelstream/15'>http://localhost:8888/primeswithparallelstream/15</a>")
                .append("\n").append("<br/>").append("<br/>").append("<a href='http://localhost:8888/primeswithforkandjoin/15'>http://localhost:8888/primeswithforkandjoin/15</a>")
                .append("\n").append("<br/>").append("<br/>").append("\n")
                .append("<a href='http://localhost:8888/primeswithforkandjoinwithstream/15'>http://localhost:8888/primeswithforkandjoin/15</a>")
                .append("\n").append("<br/>").append("<br/>").append("\n").append("</body>").append("\n").append("</html>");

        return htmlBuilder.toString();
    }
}