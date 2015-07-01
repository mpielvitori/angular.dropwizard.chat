package com.barbu.chat.resources;

import com.barbu.chat.daos.MessageDAO;
import com.barbu.chat.models.Message;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import javax.ws.rs.NotFoundException;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.BooleanParam;
import io.dropwizard.jersey.params.DateTimeParam;
import io.dropwizard.jersey.params.IntParam;
import io.dropwizard.jersey.params.LongParam;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Testentities")
public class TestEntityResource {

    private static final Logger LOG = LoggerFactory.getLogger(TestEntityResource.class);

    private final MessageDAO dao;

    public TestEntityResource(MessageDAO dao) {
        this.dao = dao;
    }

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public List<Message> getAll() {
        return dao.findAll();
    }*/

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public List<Message> get(@PathParam("id") LongParam id) {
        return dao.findRoomMessages(id.get().intValue());
    }

}
