package com.github.liucijus.dropwizard;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {
    @GET
    @Timed
    public HelloResponse sayHello(@QueryParam("name") Optional<String> name) {
        return new HelloResponse("Hello, " + name.or("anonymous"));
    }
}

