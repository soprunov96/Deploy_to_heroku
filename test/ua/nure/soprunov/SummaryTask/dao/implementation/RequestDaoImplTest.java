package ua.nure.soprunov.SummaryTask.dao.implementation;

import org.junit.*;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.dao.entity.Request;
import ua.nure.soprunov.SummaryTask.exception.DBException;

import org.apache.log4j.Logger;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class RequestDaoImplTest {

    private static final Logger LOG = Logger.getLogger(RequestDaoImplTest.class);

    private static Request request;
    private static RequestDaoImpl requestDaoImpl;
    private static long requestId;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        LOG.debug("class test starts");
        requestDaoImpl = new RequestDaoImpl(
                DataSourceFactory
                        .getDataSource(DataSourceType.MY_SQL_DATASOURCE_WITH_OUT_JNDI)) {
        };
    }


    @AfterClass
    public static void tearDownAfterClass() throws Exception {

        Request requestToDelete = new Request();
        requestToDelete.setId(requestId);
        requestDaoImpl.delete(requestToDelete);
        LOG.debug("class test finished");
    }

    @Before
    public void setUp() throws Exception {
        LOG.debug("@Before test method starts");

        request = new Request();
        request.setDriverId(3L);
        request.setCarType("sedan");
        request.setRange(500);

        new RequestDaoImpl(DataSourceFactory.getDataSource(DataSourceType.MY_SQL_DATASOURCE_WITH_OUT_JNDI)).create(request);

        requestId = request.getId();
        LOG.debug("@Before test method finished");
    }

    @After
    public void tearDown() throws Exception {
        LOG.debug("@After test method starts");

        System.out.println("@After");
        requestDaoImpl.delete(request);
        request = null;

        LOG.debug("@After test method starts");
    }


    @Test
    public void create() throws DBException {
        LOG.debug("test method starts");

        requestDaoImpl.delete(request);

        request = new Request();

        request.setDriverId(3l);
        request.setCarType("universal");
        request.setRange(700);

        requestDaoImpl.create(request);
        LOG.trace("create Request --> " + request);
        assertThat(request.getId(), not(equalTo(-1)));

        LOG.debug("test method finish");
    }

    @Test
    public void update() {

    }

    @Test
    public void delete() throws DBException {
        LOG.debug("test method starts");

        requestDaoImpl.delete(request);
        LOG.trace("delete request --> " + request);
        assertThat(requestDaoImpl.find(request.getId()), is(equalTo(null)));

        LOG.debug("test method finish");
    }

    @Test
    public void find() throws DBException {
        LOG.debug("test method starts");
        LOG.trace("find request --> " + requestDaoImpl.find(requestId));
        assertNotNull(requestDaoImpl.find(requestId));
        LOG.debug("test method finish");
    }

    @Test
    public void findAll() throws DBException {
        LOG.debug("test method starts");
        List<Request> requests = requestDaoImpl.findAll();
        LOG.trace("find all request --> " + requests);
        assertThat(requests.isEmpty(), is(false));
        LOG.debug("test method finish");
    }
}