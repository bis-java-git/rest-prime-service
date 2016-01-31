package com.bis.resource;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import com.bis.config.PrimeServiceConfiguration;
import com.bis.exception.ResourceExceptionHandler;
import com.bis.exception.PrimesExceptionHandler;
import com.bis.healthcheck.PrimesHealthCheck;
import com.bis.service.PrimeNumberServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.slf4j.LoggerFactory;

public class PrimeServiceRunner extends Application<PrimeServiceConfiguration> {

    @Override
    public void initialize(final Bootstrap<PrimeServiceConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ClasspathConfigurationSourceProvider());

        //Needed this patch as dropwizard has issue with the logging appenders
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.reset();
        ContextInitializer initializer = new ContextInitializer(context);
        try {
            initializer.autoConfig();
        } catch (JoranException ignored) {
        }
    }

    @Override
    public void run(final PrimeServiceConfiguration primeServiceConfiguration, final Environment environment) throws Exception {
        environment.jersey().register(new PrimeResource(new PrimeNumberServiceImpl()));
        environment.jersey().register(new PrimesExceptionHandler());
        environment.jersey().register(new ResourceExceptionHandler());
        environment.healthChecks().register("RestPrimesService", new PrimesHealthCheck());
        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
            }
        });
    }

    public static void main(String[] args) throws Exception {
        new PrimeServiceRunner().run("server", "configuration.yaml");
    }
}
