package com.chat.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

// Allow users to login. Add their username to an array list
@Path("/login")
@Produces("text/plain")
public class LoginResource {

    @POST
    public Response get(@PathParam("username") String username) {
        // add username to the array
        return Response.ok("Login").build();
    }
}
