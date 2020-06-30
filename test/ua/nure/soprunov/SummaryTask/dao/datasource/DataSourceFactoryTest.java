package ua.nure.soprunov.SummaryTask.dao.datasource;

//import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.Test;

import javax.sql.DataSource;

import static org.junit.Assert.*;

public class DataSourceFactoryTest {

    @Test
    public void getDataSource() {

        DataSource dataSource2 = DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE_WITH_OUT_JNDI);

//        assertEquals(dataSource2.getClass(),
//                MysqlConnectionPoolDataSource.class);
    }
}