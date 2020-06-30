package ua.nure.soprunov.SummaryTask.dao;

import ua.nure.soprunov.SummaryTask.exception.DBException;

import java.util.List;

/**
 * Main interface for all DAO objects with operations of creating, updating,
 * deleting and finding some specific object. Also all subclasses should
 * implement findAll() method, which finds all saved objects of type
 * <code>T</code> in the database.
 *
 * @param <T> type of the object
 *  @authors Soprunov Igor
 */

public interface Dao<T> {

    /**
     * Saves specified object in database.
     *
     * @param entity - object to be saved
     */
    public void create(T entity) throws DBException;

    /**
     * Updates specified object in database.
     *
     * @param entity - object to be updated
     */
    public void update(T entity) throws DBException;

    /**
     * Deletes specified object from database.
     *
     * @param entity - object to be deleted
     */
    public void delete(T entity) throws DBException;

    /**
     * Finds object in database by it's unique primary key.
     *
     * @param entityPK - primary key of the object that should be found.
     * @return object of type <code>T</code>, which unique id is equal to
     * specified.
     */
    public T find(long entityPK) throws DBException;

    /**
     * Finds all objects of type <code>T</code> in database.
     *
     * @return all objects that database has at this point of time.
     */
    public List<T> findAll() throws DBException;
}
