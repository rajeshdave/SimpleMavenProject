package com.rajesh.java.maven.rest;

import com.sun.net.httpserver.HttpServer;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class TestMain {

    //Test this service by URL: http://localhost:8080/hello/rajesh
    public static void main(String[] args) {

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build();
        ResourceConfig config = new ResourceConfig(HelloWorldService.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);

        //javax.xml.ws.Endpoint.publish("http://localhost:8000/HelloWorldService/", new HelloWorldService());

    }
}
