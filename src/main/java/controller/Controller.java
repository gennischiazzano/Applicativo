package controller;

import dao.BachecaDAOInterface;
import dao.ToDoDAOInterface;
import dao.LoginDAOInterface;
import dao.RegisterDAOInterface;

import implementazionePostgresDAO.BachecaPostgresDAO;
import implementazionePostgresDAO.ToDoPostgresDAO;
import implementazionePostgresDAO.LoginPostgresDAO;
import implementazionePostgresDAO.RegisterPostgresDAO;

import model.Bacheca_Titolo;
import model.Bacheca;
import model.ToDo;
import model.Utente;

import java.util.List;

/**
 * Controller centrale dell'applicazione ToDo.
 * Gestisce tutte le operazioni su utenti, bacheche e ToDo.
 * Comunica con i DAO tramite interfacce, senza conoscere le implementazioni concrete.
 */
public class Controller {

    private static final BachecaDAOInterface bachecaDAO = new BachecaPostgresDAO();
    private static final LoginDAOInterface loginDAO = new LoginPostgresDAO();
    private static final RegisterDAOInterface registerDAO = new RegisterPostgresDAO();
    private static final ToDoDAOInterface toDoDAO = new ToDoPostgresDAO();

    // --- UTENTE ---

    /**
     * Effettua il login dell'utente.
     * @param username username dell'utente
     * @param password password dell'utente
     * @return oggetto Utente se le credenziali sono corrette, null altrimenti
     */
    public static Utente login(String username, String password) {
        return loginDAO.verificaCredenziali(username, password);
    }

    /**
     * Registra un nuovo utente.
     * @param username username scelto
     * @param password password scelta
     * @return true se registrazione avvenuta, false se username già esistente
     */
    public static boolean registraUtente(String username, String password) {
        return registerDAO.registraUtente(username, password);
    }

    // --- BACHECA ---

    /**
     * Crea una nuova bacheca per un utente.
     */
    public static boolean creaBacheca(Bacheca_Titolo titolo, String descrizione, String utenteUsername) {
        return bachecaDAO.aggiungiBacheca(titolo, descrizione, utenteUsername);
    }

    /**
     * Recupera tutte le bacheche di un utente.
     */
    public static List<Bacheca> getBacheche(String utenteUsername) {
        return bachecaDAO.getBachecheByUtente(utenteUsername);
    }

    /**
     * Elimina una bacheca esistente.
     */
    public static boolean eliminaBacheca(int bachecaID) {
        return bachecaDAO.eliminaBacheca(bachecaID);
    }

    /**
     * Modifica la descrizione di una bacheca.
     */
    public static boolean modificaDescrizioneBacheca(int bachecaID, String nuovaDescrizione) {
        return bachecaDAO.modificaDescrizioneBacheca(bachecaID, nuovaDescrizione);
    }

    // --- TODO ---

    /**
     * Crea un nuovo ToDo in una bacheca.
     */
    public static boolean creaToDo(ToDo todo, int bachecaID) {
        return toDoDAO.creaToDo(todo, bachecaID);
    }

    /**
     * Recupera tutti i ToDo di una bacheca.
     */
    public static List<ToDo> getToDoByBacheca(int bachecaID) {
        return toDoDAO.getToDoByBacheca(bachecaID);
    }

    /**
     * Aggiorna lo stato di un ToDo (completato/non completato).
     */
    public static boolean aggiornaStatoToDo(ToDo todo, int bachecaID) {
        return toDoDAO.aggiornaStatoToDo(todo, bachecaID);
    }

    /**
     * Elimina un ToDo esistente.
     */
    public static boolean eliminaToDo(ToDo todo, int bachecaID) {
        return toDoDAO.eliminaToDo(todo, bachecaID);
    }

    /**
     * Modifica un ToDo esistente.
     */
    public static boolean modificaToDo(ToDo todo, int bachecaID) {
        return toDoDAO.modificaToDo(todo, bachecaID);
    }

    /**
     * Condivide un ToDo con un altro utente.
     */
    public static boolean condividiToDo(int todoID, String username) {
        return toDoDAO.condividiToDo(todoID, username);
    }

    /**
     * Recupera tutti i ToDo condivisi con un utente.
     */
    public static List<ToDo> getToDoCondivisi(String username) {
        return toDoDAO.getToDoCondivisi(username);
    }

    public static String getDescrizioneBacheca(int bachecaID) {
        return bachecaDAO.getDescrizioneBacheca(bachecaID);
    }
}
