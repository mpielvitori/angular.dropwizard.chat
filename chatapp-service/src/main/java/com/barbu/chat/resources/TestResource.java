package com.barbu.chat.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.params.BooleanParam;
import io.dropwizard.jersey.params.DateTimeParam;
import io.dropwizard.jersey.params.IntParam;
import io.dropwizard.jersey.params.LongParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.barbu.chat.models.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestResource {

    private static final Logger LOG = LoggerFactory.getLogger(TestResource.class);

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Message getRoomMessages(@PathParam("id") LongParam id) {
            if (id.get() != 22) {
                throw new NotFoundException("Message " + id.get() + " not found");
            }
        return null;
    }

        /*@GET
        @Path("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        @Timed
        @UnitOfWork
        public Message get(@PathParam("id") LongParam id) {
            Optional<Message> entity = dao.find(id.get());
            if (!entity.isPresent()) {
                throw new NotFoundException("Message " + id.get() + " not found");
            }
            return entity.get();
        }*/

    @Path("/join")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public String mymethod(

        @FormParam("user") String user,
        @FormParam("room") String room
        ) {
        StringBuilder sb = new StringBuilder();
        sb.append("Received parameters:\n");

        sb.append("user=");
        sb.append(user);
        sb.append("\n");

        sb.append("room=");
        sb.append(room);
        sb.append("\n");

        return sb.toString();
    }

    @Path("/sendMessage")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public String sendMessage(

        @FormParam("user") String user,
        @FormParam("room") String room,
        @FormParam("message") String message
        ) {
        StringBuilder sb = new StringBuilder();
        sb.append("Received parameters:\n");

        sb.append("user=");
        sb.append(user);
        sb.append("\n");

        sb.append("room=");
        sb.append(room);
        sb.append("\n");

        sb.append("message=");
        sb.append(message);
        sb.append("\n");

        return sb.toString();
    }

}
