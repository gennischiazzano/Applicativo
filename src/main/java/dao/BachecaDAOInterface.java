package DAO;

import Model.Bacheca;
import Model.Bacheca_Titolo;
import java.util.List;

/**
 * Interfaccia DAO per le operazioni sulle bacheche.
 * Definisce i metodi disponibili senza implementazione.
 */
public interface BachecaDAOInterface {

    /**
     * Aggiunge una nuova bacheca per un utente.
     */
    boolean aggiungiBacheca(Bacheca_Titolo titolo, String descrizione, String utenteUsername);

    /**
     * Restituisce tutte le bacheche di un utente.
     */
    List<Bacheca> getBachecheByUtente(String utenteUsername);

    /**
     * Elimina la bacheca indicata.
     */
    boolean eliminaBacheca(int bachecaID);

    /**
     * Modifica la descrizione della bacheca.
     */
    boolean modificaDescrizioneBacheca(int bachecaID, String nuovaDescrizione);

    String getDescrizioneBacheca(int bachecaID);
}