package ua.nure.soprunov.SummaryTask.dao.implementation;

import org.apache.log4j.Logger;
import org.junit.*;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.dao.entity.User;
import ua.nure.soprunov.SummaryTask.exception.DBException;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

public class UserDaoImplTest {

    private static final Logger LOG = Logger.getLogger(UserDaoImplTest.class);

    private static User user;
    private static UserDaoImpl userDaoImpl;
    private static long userId;
    private static String userLogin;


    @BeforeClass
    public static void setUpBeforeClass() {
        LOG.debug("class test starts");
        userDaoImpl = new UserDaoImpl(
                DataSourceFactory
                        .getDataSource(DataSourceType.MY_SQL_DATASOURCE_WITH_OUT_JNDI)) {
        };
    }


    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // clean up db
        User userToDelete = new User();
        userToDelete.setId(userId);
        userDaoImpl.delete(userToDelete);
        LOG.debug("class test finished");
    }

    @Before
    public void setUp() throws Exception {
        LOG.debug("@Before test method starts");
        user = new User();
        user.setLogin("Don3");
        user.setFirstName("Al");
        user.setLastName("Pacino");
        user.setPassword("1234");
        user.setRoleId(0);

        new UserDaoImpl(DataSourceFactory.getDataSource(DataSourceType.MY_SQL_DATASOURCE_WITH_OUT_JNDI)).create(user);

        // userLogin = user.getLogin();

//    user = userDao.findUserByLogin(userLogin);
        userId = user.getId();

        LOG.debug("@Before test method finished");
    }

    @After
    public void tearDown() throws Exception {
        LOG.debug("@After test method starts");
        userDaoImpl.delete(user);
        user = null;
        LOG.debug("@After test method starts");
    }

    @Test
    public void create() throws DBException {
        LOG.debug("test method starts");

        userDaoImpl.delete(user);

        user = new User();
        user.setLogin("Ivan");
        user.setFirstName("Ivanov");
        user.setLastName("Ivanovich");
        user.setPassword("1234");
        user.setRoleId(0);

        userDaoImpl.create(user);
        LOG.trace("create user --> " + user);
        assertThat(user.getId(), not(equalTo(-1)));

        userDaoImpl.delete(user);

        LOG.debug("test method finish");
    }


    @Test
    public void update() throws DBException {
        LOG.debug("test method starts");
        user.setFirstName("newLogin");
        userDaoImpl.update(user);

        assertThat(user.getFirstName(), equalTo(userDaoImpl.find(user.getId())
                .getFirstName()));
        LOG.debug("test method finish");
    }

    @Test
    public void extractUser() {

    }

    @Test
    public void findUserByLogin() throws DBException {
        LOG.debug("test method starts");
        assertNotNull(userDaoImpl.findUserByLogin(user.getLogin()));
        LOG.trace("find user by login --> " + userDaoImpl.findUserByLogin(user.getLogin()));
        LOG.debug("test method starts");

    }

    @Test
    public void find() throws DBException {
        LOG.debug("test method starts");
        assertNotNull(userDaoImpl.find(userId));
        LOG.debug("test method finish");
    }

    @Test
    public void findAll() throws DBException {
        LOG.debug("test method starts");
        List<User> users = userDaoImpl.findAll();
        LOG.trace("find all users --> " + users);
        assertThat(users.isEmpty(), is(false));
        LOG.debug("test method finish");
    }



    @Test
    public void delete() throws DBException {
        LOG.debug("test method starts");
        userDaoImpl.delete(user);
        LOG.trace("delete user --> " + user);
        assertThat(userDaoImpl.find(user.getId()), is(equalTo(null)));
        LOG.debug("test method finish");
    }
}