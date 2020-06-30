package ua.nure.soprunov.SummaryTask.dao;

import ua.nure.soprunov.SummaryTask.dao.entity.Flight;
import ua.nure.soprunov.SummaryTask.exception.DBException;

import java.util.List;

/**
 * Interface for all  FlightDao  objects extends Dao with basic crud operation.
 * In this class we can add some specific operations for FlightDao implementation
 *
 * @see ua.nure.soprunov.SummaryTask.dao.Dao
 *  @authors Soprunov Igor
 */


public interface FlightDao extends Dao <Flight> {

    /**
     * Appoint driver and car into Flight
     *
     * @param id
     * @param driverId
     * @param carId
     *
     */
    void appointDriverAndCar(String id, long driverId, long carId )throws DBException;


    /**
     * Insert request id into Flights
     *
     * @param flightId
     * @param requestId
     *
     */
    void insertIdRequestIntoFlights(long flightId, long requestId) throws DBException;



   /**
    * Returns limited sorted flight list .
    *
    * @param recordsPerPage
    * @param start
    * @param orderBy
    * @return List of flights.
    */

    List<Flight> findFlightsListSortBy(int start, int recordsPerPage, String orderBy) throws DBException;


   /**
    * Returns limited sorted flight list  where request_id IS NULL
    *
    * @param recordsPerPage
    * @param start
    * @param orderBy
    * @return List of flights.
    */
   public  List<Flight> findFlightsListWhereSortBy(int start, int recordsPerPage, String orderBy) throws DBException;


   /**
    * Find flight by request id
    *
    * @param id
    * @return flight
    */

   Flight findFlightByRequestId(long id) throws DBException;

}
