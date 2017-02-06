package ru.siblion.nesterov.client.dbService;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by alexander on 06.02.2017.
 */

public class DBService {
    static DBService dbService = null;

    private Connection connection;

    private DBService() {
        connection = getConnection();
    }

    private Connection getConnection() {
        try {
            Context contex = new InitialContext();
            DataSource dataSource = (DataSource) contex.lookup("jdbc/mysql_ds");
            connection = dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static DBService getInstance() {
        if (dbService == null) {
            dbService = new DBService();
        }
        return dbService;
    }
}
