package implementazionePostgresDAO;

import DAO.ToDoDAOInterface;
import Model.ToDo;
import Model.Stato_ToDo;
import database.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione DAO PostgreSQL per i ToDo.
 * Contiene tutte le query per creare, leggere, aggiornare, eliminare e condividere ToDo.
 */
public class ToDoPostgresDAO implements ToDoDAOInterface {

    @Override
    public boolean creaToDo(ToDo todo, int bachecaID) {
        String sql = "INSERT INTO ToDo (Titolo, Descrizione, Url, Data_Scadenza, Stato, Ordine, Bacheca_ID) " +
                "VALUES (?, ?, ?, ?, ?::Stato_ToDo, ?, ?)";
        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, todo.getTitolo());
            ps.setString(2, todo.getDescrizione());
            ps.setString(3, todo.getUrl());
            if (todo.getDataScadenza() != null && !todo.getDataScadenza().isEmpty()) {
                ps.setDate(4, Date.valueOf(todo.getDataScadenza()));
            } else {
                ps.setNull(4, Types.DATE);
            }
            ps.setObject(5, todo.getStato().name(), Types.OTHER);
            ps.setInt(6, todo.getOrdine());
            ps.setInt(7, bachecaID);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ToDo> getToDoByBacheca(int bachecaID) {
        List<ToDo> lista = new ArrayList<>();
        String sql = "SELECT * FROM ToDo WHERE Bacheca_ID = ? ORDER BY Ordine";
        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bachecaID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ToDo todo = new ToDo(
                        rs.getString("Titolo"),
                        rs.getString("Descrizione"),
                        rs.getString("Immagine"),
                        rs.getString("Url"),
                        rs.getDate("Data_Scadenza") != null ? rs.getDate("Data_Scadenza").toString() : null,
                        Stato_ToDo.valueOf(rs.getString("Stato")),
                        rs.getInt("Ordine"),
                        bachecaID
                );
                todo.setId(rs.getInt("id"));
                lista.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean aggiornaStatoToDo(ToDo todo, int bachecaID) {
        String sql = "UPDATE ToDo SET Stato = ?::Stato_ToDo WHERE Titolo = ? AND Bacheca_ID = ?";
        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, todo.getStato().name(), Types.OTHER);
            ps.setString(2, todo.getTitolo());
            ps.setInt(3, bachecaID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminaToDo(ToDo todo, int bachecaID) {
        String sql = "DELETE FROM ToDo WHERE Ordine = ? AND Bacheca_ID = ?";
        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, todo.getOrdine());
            ps.setInt(2, bachecaID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificaToDo(ToDo todo, int bachecaID) {
        String sql = "UPDATE ToDo SET Titolo = ?, Descrizione = ?, Data_Scadenza = ? WHERE Ordine = ? AND Bacheca_ID = ?";
        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, todo.getTitolo());
            ps.setString(2, todo.getDescrizione());
            if (todo.getDataScadenza() != null && !todo.getDataScadenza().isEmpty()) {
                ps.setDate(3, Date.valueOf(todo.getDataScadenza()));
            } else {
                ps.setNull(3, Types.DATE);
            }
            ps.setInt(4, todo.getOrdine());
            ps.setInt(5, bachecaID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean condividiToDo(int todoID, String username) {
        String sql = "INSERT INTO utente_todo (todo_id, username) VALUES (?, ?)";
        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, todoID);
            ps.setString(2, username);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ToDo> getToDoCondivisi(String username) {
        List<ToDo> lista = new ArrayList<>();
        String sql = "SELECT t.* FROM ToDo t JOIN utente_todo ut ON t.id = ut.todo_id WHERE ut.username = ? ORDER BY t.Ordine";
        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ToDo todo = new ToDo(
                        rs.getString("Titolo"),
                        rs.getString("Descrizione"),
                        null,
                        rs.getString("Url"),
                        rs.getDate("Data_Scadenza") != null ? rs.getDate("Data_Scadenza").toString() : null,
                        Stato_ToDo.valueOf(rs.getString("Stato")),
                        rs.getInt("Ordine"),
                        rs.getInt("Bacheca_ID")
                );
                todo.setId(rs.getInt("id"));
                lista.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}