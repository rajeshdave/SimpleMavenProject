package com.rajesh.java.maven.rest;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@WebService
@Path("/hello")
public class HelloWorldService {

    @GET
    @Path("/{param}")
    @Produces("text/plain")
    public String getMsg(@PathParam("param") String msg) {

        String output = "Jersey say : " + msg;
        return output;

        //return Response.status(200).entity(output).build();

    }

}
