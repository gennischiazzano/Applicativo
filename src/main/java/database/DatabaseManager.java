package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestisce la connessione al database PostgreSQL.
 * Centralizza URL, username e password.
 */
public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/ToDo";
    private static final String USER = "postgres";
    private static final String PASSWORD = "genni04";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver PostgreSQL non trovato!", e);
        }
    }

    /**
     * Restituisce una nuova connessione al database.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
