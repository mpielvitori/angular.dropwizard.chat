package com.barbu.chat.daos;

import com.google.common.base.Optional;
import com.barbu.chat.models.Room;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * A DAO for managing {@link Room} objects.
 */
public class RoomDAO extends AbstractDAO<Room> {

    /**
     * Creates a new DAO with the given session provider.
     *
     * @param provider a session provider
     */
    public RoomDAO(SessionFactory provider) {
        super(provider);
    }

    /**
     * Returns the {@link Room} with the given ID.
     *
     * @param id the entity ID
     * @return the entity with the given ID
     */
    public Optional<Room> find(long id) {
        return Optional.fromNullable(get(id));
    }

    /**
     * Returns all {@link Room} entities.
     *
     * @return the list of entities
     */
    public List<Room> findAll() {
        return (List<Room>) criteria().list();
    }

    /**
     * Saves the given {@link Room}.
     *
     * @param entity the entity to save
     * @return the persistent entity
     */
    public Room save(Room entity) throws HibernateException {
        return persist(entity);
    }

    /**
     * Merges the given {@link Room}.
     *
     * @param entity the entity to merge
     * @return the persistent entity
     */
    public Room merge(Room entity) throws HibernateException {
        return (Room) currentSession().merge(entity);
    }

    /**
     * Deletes the given {@link Room}.
     *
     * @param entity the entity to delete
     */
    public void delete(Room entity) throws HibernateException {
        currentSession().delete(entity);
    }
}
