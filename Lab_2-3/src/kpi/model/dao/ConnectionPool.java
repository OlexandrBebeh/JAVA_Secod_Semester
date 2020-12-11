package kpi.model.dao;


import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static volatile DataSource dataSource;

    private static DataSource getDataSource(){
        if(dataSource==null){
            synchronized (ConnectionPool.class){
                if(dataSource==null){
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
                    ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
                    ds.setUsername("c##ConnectionJava");
                    ds.setPassword("JAVA");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(20);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}