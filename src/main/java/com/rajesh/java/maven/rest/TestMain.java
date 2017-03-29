package com.rajesh.java.maven.rest;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class TestMain {

    //Test this service by URL: http://localhost:8080/rest/hello/rajesh (Authentication header is required, test this by TestClient class)
    public static void main(String[] args) {

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).uri("rest").build();
        ResourceConfig config = new CustomApplication();
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
    }
}
