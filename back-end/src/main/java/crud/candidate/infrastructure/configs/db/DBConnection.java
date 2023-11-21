package crud.candidate.infrastructure.configs.db;

import crud.candidate.domain.utils.Functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class DBConnection {

    private static Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/candidate";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static final String CONNECTED_TO_DATABASE = "Connected to the database";
    private static final String ERROR_WHILE_CONNECTING_TO_DATABASE = "Error while attempting to connect to Database";

    public static Connection getConnection() {
        Functions.ifEmptyDo(
                connection,
                DBConnection::connect
        );
        return connection;
    }

    private static void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(CONNECTED_TO_DATABASE);
        } catch (SQLException exception) {
            System.out.println(ERROR_WHILE_CONNECTING_TO_DATABASE);
        }
    }

}
