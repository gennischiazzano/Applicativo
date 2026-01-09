package DAO;

import Model.Utente;

/**
 * Interfaccia DAO per la gestione del login degli utenti.
 */
public interface LoginDAOInterface {

    /**
     * Verifica le credenziali dell'utente.
     * @param username username inserito
     * @param password password inserita
     * @return oggetto Utente se le credenziali sono corrette, null altrimenti
     */
    Utente verificaCredenziali(String username, String password);
}