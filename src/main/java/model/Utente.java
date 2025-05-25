package Model;

public class Utente {
    //Attributi

    private String username ;
    private String password ;

    //Costruttore
    public Utente(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
