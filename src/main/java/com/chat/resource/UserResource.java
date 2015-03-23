package com.chat.resource;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    public static List<String> users = new ArrayList<String>();

    @GET
    public Response get() {
        return Response.ok(users).build();
    }

    @POST
    public Response create(@Valid Login login) {
        users.add(login.getUsername());
        return Response.ok().build();
    }

    private static class Login {
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}