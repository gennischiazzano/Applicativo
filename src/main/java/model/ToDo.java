package Model;

import java.util.ArrayList;
import java.util.List;

/** Classe che rappresenta un ToDo. */
public class ToDo {

    // Attributi
    private String titolo;
    private String dataScadenza;
    private String descrizione;
    private String immagine;
    private String url;
    private Stato_ToDo stato;
    private int ordine;
    private int bachecaID;
    private List<Operazione> OperazioneSuToDo;
    private int id;

    /**
     * Costruttore ToDo.
     * @param titolo titolo dell’attività
     * @param descrizione descrizione dell’attività
     * @param immagine URL immagine (opzionale)
     * @param url URL esterno (opzionale)
     * @param dataScadenza data di scadenza in formato YYYY-MM-DD
     * @param stato stato iniziale del ToDo
     * @param ordine ordine nella lista della bacheca
     * @param bachecaID ID della bacheca a cui appartiene
     */
    public ToDo(String titolo, String descrizione, String immagine, String url, String dataScadenza, Stato_ToDo stato, int ordine, int bachecaID) {
        this.titolo = titolo;
        this.dataScadenza = dataScadenza;
        this.descrizione = descrizione;
        this.immagine = immagine;
        this.url = url;
        this.stato = stato;
        this.ordine = ordine;
        this.bachecaID = bachecaID;
        this.OperazioneSuToDo = new ArrayList<>();
    }

    // --- Getters e Setters ---
    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public String getImmagine() { return immagine; }
    public void setImmagine(String immagine) { this.immagine = immagine; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getDataScadenza() { return dataScadenza; }
    public void setDataScadenza(String dataScadenza) { this.dataScadenza = dataScadenza; }

    public Stato_ToDo getStato() { return stato; }
    public void setStato(Stato_ToDo stato) { this.stato = stato; }

    public int getOrdine() { return ordine; }
    public void setOrdine(int ordine) { this.ordine = ordine; }

    public int getBachecaID() { return bachecaID; }
    public void setBachecaID(int bachecaID) { this.bachecaID = bachecaID; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public List<Operazione> getOperazioniSuToDo() { return OperazioneSuToDo; }
    public void setOperazioniSuToDo(List<Operazione> OperazioneSuToDo) { this.OperazioneSuToDo = OperazioneSuToDo; }
}
