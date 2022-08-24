package be.rubus.quarkus;

import be.rubus.quarkus.integration.ExampleBean;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    ExampleBean exampleBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello with extension. Bean Value: " + exampleBean.getValue();
    }
}