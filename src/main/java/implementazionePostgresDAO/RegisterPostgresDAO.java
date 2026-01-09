package implementazionePostgresDAO;

import DAO.RegisterDAOInterface;
import database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementazione DAO PostgreSQL per la registrazione degli utenti.
 * Controlla se l'utente esiste già e inserisce un nuovo record nel DB.
 */
public class RegisterPostgresDAO implements RegisterDAOInterface {

    @Override
    public boolean registraUtente(String username, String password) {
        try (Connection conn = DatabaseManager.getConnection()) {
            // Controlla se l'utente esiste già
            String checkSql = "SELECT * FROM utente WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return false; // Username già esistente
            }

            // Inserisce nuovo utente
            String insertSql = "INSERT INTO utente (username, password) VALUES (?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, username);
            insertStmt.setString(2, password); // Puoi sostituire con hash se vuoi sicurezza
            insertStmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}