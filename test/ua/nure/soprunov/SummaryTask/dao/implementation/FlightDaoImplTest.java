package ua.nure.soprunov.SummaryTask.dao.implementation;

import org.junit.*;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.dao.entity.Flight;
import ua.nure.soprunov.SummaryTask.exception.DBException;

import org.apache.log4j.Logger;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class FlightDaoImplTest {

    private static final Logger LOG = Logger.getLogger(FlightDaoImplTest.class);

    private static Flight flight;
    private static FlightDaoImpl flightDaoImpl;
    private static long flightId;


    @BeforeClass
    public static void setUpBeforeClass() {
        LOG.debug("class test starts");

        flightDaoImpl = new FlightDaoImpl(
                DataSourceFactory
                        .getDataSource(DataSourceType.MY_SQL_DATASOURCE_WITH_OUT_JNDI)) {
        };
    }


    @AfterClass
    public static void tearDownAfterClass() throws Exception {

        // clean up db
        Flight flightToDelete = new Flight();
        flightToDelete.setId(flightId);
        flightDaoImpl.delete(flightToDelete);
        LOG.debug("class test finished");
    }

    @Before
    public void setUp() throws Exception {
        LOG.debug("@Before test method starts");

        flight = new Flight();
        flight.setName("Test flight");
        flight.setDate("22.02.2010");
        flight.setArrival("New York");
        flight.setDepart("Kiev");
        flight.setStatus("open");
        new FlightDaoImpl(DataSourceFactory.getDataSource(DataSourceType.MY_SQL_DATASOURCE_WITH_OUT_JNDI)).create(flight);

        flightId = flight.getId();

        LOG.debug("@Before test method finished");
    }

    @After
    public void tearDown() throws Exception {
        LOG.debug("@After test method starts");

        flightDaoImpl.delete(flight);
        flight = null;

        LOG.debug("@After test method starts");
    }


    @Test
    public void create() throws DBException {
        LOG.debug("test method starts");

        flightDaoImpl.delete(flight);

        flight = new Flight();

        flight.setName("Second flight");
        flight.setDate("22.02.2018");
        flight.setArrival("Nikolaev");
        flight.setDepart("Kiev");
        flight.setStatus("open");

        LOG.trace("create flight --> " + flight);

        flightDaoImpl.create(flight);

        assertThat(flight.getId(), not(equalTo(-1)));
        LOG.debug("test method finish");
    }

    @Test
    public void update() throws DBException {
        LOG.debug("test method starts");
        flight.setName("new name");

        flightDaoImpl.update(flight);
        LOG.trace("update flight --> " + flight);
        assertThat(flight.getName(), equalTo(flightDaoImpl.find(flight.getId()).getName()));
        LOG.debug("test method finish");
    }

    @Test
    public void delete() throws DBException {
        LOG.debug("test method starts");
        flightDaoImpl.delete(flight);
        LOG.trace("delete flight --> " + flight);
        assertThat(flightDaoImpl.find(flight.getId()), is(equalTo(null)));
        LOG.debug("test method finish");
    }

    @Test
    public void find() throws DBException {
        LOG.debug("test method starts");
        LOG.trace("find flight --> " + flightDaoImpl.find(flightId));
        assertNotNull(flightDaoImpl.find(flightId));
        LOG.debug("test method finish");
    }

    @Test
    public void findAll() throws DBException {
        LOG.debug("test method starts");
        List<Flight> flights = flightDaoImpl.findAll();
        LOG.trace("find all flights --> " + flights);
        assertThat(flights.isEmpty(), is(false));
        LOG.debug("test method finish");
    }

    @Test
    public void appointDriverAndCar() throws DBException {
        LOG.debug("test method starts");

        long carId = 1;
        long driverId = 3;
        flight.setDriverId(driverId);
        flight.setVehicleId(carId);
        flightDaoImpl.appointDriverAndCar(flight.getId().toString(), driverId, carId);
        LOG.trace("update flight --> " + flight);
        assertThat(flight.getDriverId(), equalTo(flightDaoImpl.find(flight.getId()).getDriverId()));

        LOG.debug("test method finish");
    }

    @Test
    public void insertIdRequestIntoFlights() throws DBException {
        LOG.debug("test method starts");

        long requestId = 1;

        flight.setRequestId(requestId);

        flightDaoImpl.insertIdRequestIntoFlights(flightId, requestId);
        LOG.trace("update flight --> " + flight);
        assertThat(flight.getRequestId(), equalTo(flightDaoImpl.find(flight.getId()).getRequestId()));

        LOG.debug("test method finish");
    }

    @Test
    public void findFlightByRequestId() throws DBException {
        LOG.debug("test method starts");

        long requestId = 1;

        flight.setRequestId(requestId);

        flightDaoImpl.insertIdRequestIntoFlights(flightId, requestId);
        LOG.trace("update flight --> " + flight);

        LOG.trace("find flight --> " + flightDaoImpl.find(flightId));
        assertNotNull(flightDaoImpl.findFlightByRequestId(requestId));
        LOG.debug("test method finish");
    }


}