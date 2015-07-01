package com.barbu.chat.daos;

import com.google.common.base.Optional;
import com.barbu.chat.models.Message;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;

import java.util.List;

/**
 * A DAO for managing {@link Message} objects.
 */
public class MessageDAO extends AbstractDAO<Message> {

    /**
     * Creates a new DAO with the given session provider.
     *
     * @param provider a session provider
     */
    public MessageDAO(SessionFactory provider) {
        super(provider);
    }

    /**
     * Returns the {@link Message} with the given ID.
     *
     * @param id the entity ID
     * @return the entity with the given ID
     */
    public Optional<Message> find(long id) {
        return Optional.fromNullable(get(id));
    }

    /**
     * Returns all {@link Message} entities.
     *
     * @return the list of entities
     */
    public List<Message> findAll() {
        return (List<Message>) criteria().list();
    }

    /**
     * Returns all {@link Message} entities.
     *
     * @return the list of entities from a given room
     */
    public List<Message> findRoomMessages(int id) {
        Criteria criteria = criteria();
        criteria.add(Restrictions.eq("room", id));
        return (List<Message>) criteria.list();
    }

    /**
     * Saves the given {@link Message}.
     *
     * @param entity the entity to save
     * @return the persistent entity
     */
    public Message save(Message entity) throws HibernateException {
        return persist(entity);
    }

    /**
     * Merges the given {@link Message}.
     *
     * @param entity the entity to merge
     * @return the persistent entity
     */
    public Message merge(Message entity) throws HibernateException {
        return (Message) currentSession().merge(entity);
    }

    /**
     * Deletes the given {@link Message}.
     *
     * @param entity the entity to delete
     */
    public void delete(Message entity) throws HibernateException {
        currentSession().delete(entity);
    }
}
