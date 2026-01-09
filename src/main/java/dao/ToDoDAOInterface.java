package dao;

import model.ToDo;
import java.util.List;

/**
 * Interfaccia DAO per le operazioni sui ToDo.
 */
public interface ToDoDAOInterface {

    /** Crea un nuovo ToDo in una bacheca. */
    boolean creaToDo(ToDo todo, int bachecaID);

    /** Recupera tutti i ToDo di una bacheca. */
    List<ToDo> getToDoByBacheca(int bachecaID);

    /** Aggiorna lo stato di un ToDo. */
    boolean aggiornaStatoToDo(ToDo todo, int bachecaID);

    /** Elimina un ToDo esistente. */
    boolean eliminaToDo(ToDo todo, int bachecaID);

    /** Modifica un ToDo esistente. */
    boolean modificaToDo(ToDo todo, int bachecaID);

    /** Condivide un ToDo con un altro utente. */
    boolean condividiToDo(int todoID, String username);

    /** Recupera tutti i ToDo condivisi con un utente. */
    List<ToDo> getToDoCondivisi(String username);

}
