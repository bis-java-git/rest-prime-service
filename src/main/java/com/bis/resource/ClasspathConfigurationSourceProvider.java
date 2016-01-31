package com.bis.resource;

import java.io.IOException;
import java.io.InputStream;

public class ClasspathConfigurationSourceProvider implements io.dropwizard.configuration.ConfigurationSourceProvider {

    @Override
    public InputStream open(String path) throws IOException {
        InputStream resourceAsStream = ClasspathConfigurationSourceProvider.class.getClassLoader().getResourceAsStream(path);

        if (resourceAsStream == null) {
            throw new IllegalArgumentException("could not find com.bis.resource [path=" + path + "]");
        }

        return resourceAsStream;
    }
}
