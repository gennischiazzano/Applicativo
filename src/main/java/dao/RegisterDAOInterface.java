package dao;

/**
 * Interfaccia DAO per la registrazione degli utenti.
 */
public interface RegisterDAOInterface {

    /**
     * Registra un nuovo utente.
     * @param username username scelto
     * @param password password scelta
     * @return true se registrazione avvenuta, false se username già esistente
     */
    boolean registraUtente(String username, String password);

}
