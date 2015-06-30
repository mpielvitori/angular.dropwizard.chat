package com.barbu.chat.daos;

import com.google.common.base.Optional;
import com.barbu.chat.models.TestEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * A DAO for managing {@link TestEntity} objects.
 */
public class TestEntityDAO extends AbstractDAO<TestEntity> {

    /**
     * Creates a new DAO with the given session provider.
     *
     * @param provider a session provider
     */
    public TestEntityDAO(SessionFactory provider) {
        super(provider);
    }

    /**
     * Returns the {@link TestEntity} with the given ID.
     *
     * @param id the entity ID
     * @return the entity with the given ID
     */
    public Optional<TestEntity> find(long id) {
        return Optional.fromNullable(get(id));
    }

    /**
     * Returns all {@link TestEntity} entities.
     *
     * @return the list of entities
     */
    public List<TestEntity> findAll() {
        return (List<TestEntity>) criteria().list();
    }

    /**
     * Saves the given {@link TestEntity}.
     *
     * @param entity the entity to save
     * @return the persistent entity
     */
    public TestEntity save(TestEntity entity) throws HibernateException {
        return persist(entity);
    }

    /**
     * Merges the given {@link TestEntity}.
     *
     * @param entity the entity to merge
     * @return the persistent entity
     */
    public TestEntity merge(TestEntity entity) throws HibernateException {
        return (TestEntity) currentSession().merge(entity);
    }

    /**
     * Deletes the given {@link TestEntity}.
     *
     * @param entity the entity to delete
     */
    public void delete(TestEntity entity) throws HibernateException {
        currentSession().delete(entity);
    }
}
