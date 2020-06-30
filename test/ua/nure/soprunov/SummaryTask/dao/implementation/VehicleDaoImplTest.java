package ua.nure.soprunov.SummaryTask.dao.implementation;

import org.apache.log4j.Logger;
import org.junit.*;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.dao.entity.Vehicle;
import ua.nure.soprunov.SummaryTask.exception.DBException;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

public class VehicleDaoImplTest {

    private static final Logger LOG = Logger.getLogger(FlightDaoImplTest.class);

    private static Vehicle vehicle;
    private static VehicleDaoImpl vehicleDaoImpl;
    private static long vehicleId;


    @BeforeClass
    public static void setUpBeforeClass() {
        LOG.debug("class test starts");
        vehicleDaoImpl = new VehicleDaoImpl(
                DataSourceFactory
                        .getDataSource(DataSourceType.MY_SQL_DATASOURCE_WITH_OUT_JNDI)) {
        };
    }


    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // clean up db
        Vehicle vehicleToDelete = new Vehicle();
        vehicleToDelete.setId(vehicleId);
        vehicleDaoImpl.delete(vehicleToDelete);
        LOG.debug("class test finished");
    }

    @Before
    public void setUp() throws Exception {
        LOG.debug("@Before test method starts");

        vehicle = new Vehicle();
        vehicle.setStatus("ready");
        vehicle.setModel("BMV");
        vehicle.setRange(300);
        vehicle.setType("sedan");

        new VehicleDaoImpl(DataSourceFactory.getDataSource(DataSourceType.MY_SQL_DATASOURCE_WITH_OUT_JNDI)).create(vehicle);

        vehicleId = vehicle.getId();

        LOG.debug("@Before test method finished");
    }

    @After
    public void tearDown() throws Exception {
        LOG.debug("@After test method starts");

        vehicleDaoImpl.delete(vehicle);
        vehicle = null;
        LOG.debug("@After test method starts");
    }


    @Test
    public void create() throws DBException {
        LOG.debug("test method starts");

        vehicleDaoImpl.delete(vehicle);

        vehicle = new Vehicle();

        vehicle.setStatus("ready");
        vehicle.setModel("AUDI");
        vehicle.setRange(500);
        vehicle.setType("sedan");

        vehicleDaoImpl.create(vehicle);
        LOG.trace("find vehicle --> " + vehicle);

        assertThat(vehicle.getId(), not(equalTo(-1)));

        LOG.debug("test method finish");
    }

    @Test
    public void update() throws DBException {
        LOG.debug("test method starts");

        vehicle.setRange(600);
        vehicleDaoImpl.update(vehicle);
        LOG.trace("update vehicle --> " + vehicle);
        assertThat(vehicle.getRange(), equalTo(vehicleDaoImpl.find(vehicle.getId()).getRange()));

        LOG.debug("test method finish");
    }

    @Test
    public void delete() throws DBException {
        LOG.debug("test method starts");

        vehicleDaoImpl.delete(vehicle);
        LOG.trace("delete vehicle --> " + vehicle);
        assertThat(vehicleDaoImpl.find(vehicle.getId()), is(equalTo(null)));

        LOG.debug("test method finish");
    }

    @Test
    public void find() throws DBException {
        LOG.debug("test method starts");
        assertNotNull(vehicleDaoImpl.find(vehicleId));
        LOG.debug("test method finish");
    }

    @Test
    public void findAll() throws DBException {
        LOG.debug("test method starts");

        List<Vehicle> vehicles = vehicleDaoImpl.findAll();
        LOG.trace("find all vehicles --> " + vehicles);
        assertThat(vehicles.isEmpty(), is(false));

        LOG.debug("test method finish");
    }
}