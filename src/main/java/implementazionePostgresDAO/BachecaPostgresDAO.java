package implementazionePostgresDAO;

import DAO.BachecaDAOInterface;
import Model.Bacheca;
import Model.Bacheca_Titolo;
import database.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione DAO PostgreSQL per le bacheche.
 * Contiene le query SQL concrete.
 */
public class BachecaPostgresDAO implements BachecaDAOInterface {

    @Override
    public boolean aggiungiBacheca(Bacheca_Titolo titolo, String descrizione, String utenteUsername) {
        String sql = "INSERT INTO bacheche (titolo, descrizione, username) VALUES (?::Bacheca_Titolo, ?, ?)";
        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, titolo.name());
            ps.setString(2, descrizione);
            ps.setString(3, utenteUsername);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Bacheca> getBachecheByUtente(String utenteUsername) {
        List<Bacheca> lista = new ArrayList<>();
        String sql = "SELECT * FROM bacheche WHERE username = ?";
        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, utenteUsername);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bacheca b = new Bacheca();
                b.setId(rs.getInt("id"));
                b.setTitolo(rs.getString("titolo"));
                b.setDescrizione(rs.getString("descrizione"));
                b.setUtenteUsername(rs.getString("username"));
                lista.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean eliminaBacheca(int bachecaID) {
        String sql = "DELETE FROM bacheche WHERE id = ?";
        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bachecaID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificaDescrizioneBacheca(int bachecaID, String nuovaDescrizione) {
        String sql = "UPDATE bacheche SET descrizione = ? WHERE id = ?";
        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuovaDescrizione);
            ps.setInt(2, bachecaID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getDescrizioneBacheca(int bachecaID) {
        String sql = "SELECT descrizione FROM bacheche WHERE id = ?";
        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bachecaID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("descrizione");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ""; // ritorna stringa vuota se errore o nulla
    }
}
