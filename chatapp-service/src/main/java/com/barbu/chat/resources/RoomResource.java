package com.barbu.chat.resources;

import com.barbu.chat.daos.RoomDAO;
import com.barbu.chat.models.Room;
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

@Path("/Rooms")
public class RoomResource {

    private static final Logger LOG = LoggerFactory.getLogger(RoomResource.class);

    private final RoomDAO dao;

    public RoomResource(RoomDAO dao) {
        this.dao = dao;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Room create(Room entity) {
        return dao.save(entity);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public List<Room> getAll() {
        return dao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Room get(@PathParam("id") LongParam id) {
        Optional<Room> entity = dao.find(id.get());
        if (!entity.isPresent()) {
            throw new NotFoundException("Room " + id.get() + " not found");
        }
        return entity.get();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Room update(@PathParam("id") LongParam id, Room entity) {
        Optional<Room> ent = dao.find(id.get());
        if (!ent.isPresent()) {
            throw new NotFoundException("Room " + id.get() + " not found");
        }
        return dao.merge(entity);
    }

    @DELETE
    @Path("{id}")
    @Timed
    @UnitOfWork
    public void delete(@PathParam("id") LongParam id) {
        Optional<Room> entity = dao.find(id.get());
        if (!entity.isPresent()) {
            throw new NotFoundException("Room " + id.get() + " not found");
        }
        dao.delete(entity.get());
    }
}
