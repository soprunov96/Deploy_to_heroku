package ua.nure.soprunov.SummaryTask.dao;

import ua.nure.soprunov.SummaryTask.dao.entity.Vehicle;
import ua.nure.soprunov.SummaryTask.exception.DBException;

import java.util.List;

/**
 * Interface for all  VehicleDao  objects extends Dao with basic crud operation.
 * In this class we can add some specific operations for VehicleDao implementation
 *
 * @see ua.nure.soprunov.SummaryTask.dao.Dao
 *  @authors Soprunov Igor
 */


public interface VehicleDao extends  Dao <Vehicle> {

    /**
     * Find Vehicle list in database by order, and specified limit
     *
     * @param start
     * @param recordsPerPage
     * @param orderBy
     *
     * @return list of Vehicle order by value
     */

    public List<Vehicle> findVechicleListSortBy(int start, int recordsPerPage, String orderBy) throws DBException;


}
