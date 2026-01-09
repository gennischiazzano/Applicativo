package implementazionePostgresDAO;

import dao.LoginDAOInterface;
import model.Utente;
import database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementazione DAO PostgreSQL per il login degli utenti.
 * Contiene la query per verificare username e password.
 */
public class LoginPostgresDAO implements LoginDAOInterface {

    @Override
    public Utente verificaCredenziali(String username, String password) {
        String sql = "SELECT * FROM utente WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Utente(username, password);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
