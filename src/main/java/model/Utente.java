package Model;

/** Classe che rappresenta un utente dell’applicazione. */
public class Utente {

    private String username;
    private String password;

    /**
     * Costruttore Utente.
     * @param username nome utente
     * @param password password
     */
    public Utente(String username, String password){
        this.username = username;
        this.password = password;
    }

    // --- Getters ---
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
