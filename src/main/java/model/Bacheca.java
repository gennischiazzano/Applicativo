package Model;

import java.util.ArrayList;
import java.util.List;

public class Bacheca {
    private int id;
    private String titolo;
    private String tipologia;
    private String utenteUsername;  // cambiato da int utenteId a String utenteUsername
    private String descrizione;
    private List<Operazione> operazioniSuBacheca;

    // Costruttore vuoto (necessario per il controller)
    public Bacheca() {
        this.operazioniSuBacheca = new ArrayList<>();
    }

    // Costruttore completo (utile per creare oggetti più velocemente)
    public Bacheca(int id, String titolo, String tipologia, String descrizione, String utenteUsername) {
        this.id = id;
        this.titolo = titolo;
        this.tipologia = tipologia;
        this.descrizione = descrizione;
        this.utenteUsername = utenteUsername;
        this.operazioniSuBacheca = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getUtenteUsername() {
        return utenteUsername;
    }

    public void setUtenteUsername(String utenteUsername) {
        this.utenteUsername = utenteUsername;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Operazione> getOperazioniSuBacheca() {
        return operazioniSuBacheca;
    }

    public void setOperazioniSuBacheca(List<Operazione> operazioniSuBacheca) {
        this.operazioniSuBacheca = operazioniSuBacheca;
    }
}
