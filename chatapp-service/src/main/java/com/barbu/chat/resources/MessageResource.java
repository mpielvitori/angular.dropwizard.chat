package com.barbu.chat.resources;

import com.barbu.chat.daos.MessageDAO;
import com.barbu.chat.models.Message;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import javax.ws.rs.NotFoundException;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.BooleanParam;
import io.dropwizard.jersey.params.IntParam;
import io.dropwizard.jersey.params.LongParam;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Messages")
public class MessageResource {

    private static final Logger LOG = LoggerFactory.getLogger(MessageResource.class);

    private final MessageDAO dao;

    public MessageResource(MessageDAO dao) {
        this.dao = dao;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Message create(Message entity) {
        return dao.save(entity);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public List<Message> getAll() {
        return dao.findAll();
    }

    @GET
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
    }

    /*@GET
    @Path("{room}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Message getRoomMessages(@PathParam("room") LongParam roomId) {
        return dao.findAll(roomId);
    }*/

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Message update(@PathParam("id") LongParam id, Message entity) {
        Optional<Message> ent = dao.find(id.get());
        if (!ent.isPresent()) {
            throw new NotFoundException("Message " + id.get() + " not found");
        }
        return dao.merge(entity);
    }

    @DELETE
    @Path("{id}")
    @Timed
    @UnitOfWork
    public void delete(@PathParam("id") LongParam id) {
        Optional<Message> entity = dao.find(id.get());
        if (!entity.isPresent()) {
            throw new NotFoundException("Message " + id.get() + " not found");
        }
        dao.delete(entity.get());
    }
}
