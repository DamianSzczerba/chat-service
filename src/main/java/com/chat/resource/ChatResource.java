package com.chat.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/index")
@Produces("text/plain")
public class ChatResource {

    @POST
    public Response get() {

        // use static for the array list. It means that the variable or function is shared between all instances of
        // that class as it belongs to the type, not the actual objects themselves
        List<String> usersOnline = new ArrayList<String>();

        return Response.ok("Login").build();
    }
}