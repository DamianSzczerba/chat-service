package com.chat.resource;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Save and retrieve chat logs
@Path("/userChat")
@Produces(MediaType.APPLICATION_JSON)
public class ChatLogsResource {
    public static Map<String, String> usersLogs = new HashMap<String, String>();

    @GET
    public Response getChatLogs() {
        return Response.ok(usersLogs).build();
    }

    @POST
    public Response addChatLogs(@Valid Logs logs) {
        String senderTest = logs.getSender();
        String receiverTest = logs.getReceiver();
        String messageTest = logs.getMessage();
        String key = senderTest + "-" + receiverTest;
        usersLogs.put(key, messageTest);
        return Response.ok().build();
    }

    private static class Logs {
        private String sender;
        private String receiver;
        private String message;

        public String getSender() {
            return sender;
        }

        public String getReceiver() {
            return receiver;
        }

        public String getMessage() {
            return message;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}