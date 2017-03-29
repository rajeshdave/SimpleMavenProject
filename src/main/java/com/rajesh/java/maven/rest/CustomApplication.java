package com.rajesh.java.maven.rest;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by rajesh on 29/03/2017.
 */
public class CustomApplication extends ResourceConfig {
    public CustomApplication() {
        packages("com.rajesh.java.maven.rest");
        register(AuthenticationFilter.class);
    }
}