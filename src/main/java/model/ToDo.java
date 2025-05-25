package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ToDo {
    //Attributi
    private String titolo;
    private String dataScadenza;
    private String descrizione;
    private String immagine;
    private String url;
    private Stato_ToDo stato;
    private int ordine;
    private List<Operazione> OperazioneSuToDO;
    //Costruttore

    public ToDo(String titolo, String descrizione, String immagine, String url, String dataScadenza, Stato_ToDo stato, int ordine){

        this.titolo = titolo;
        this.dataScadenza = dataScadenza;
        this.descrizione = descrizione;
        this.immagine = immagine;
        this.url = url;
        this.stato = stato;
        this.ordine = ordine;
        this.OperazioneSuToDO = new ArrayList<>();
    }
    public String getTitolo() { return titolo; }
    public String getDescrizione() { return descrizione; }
    public String getImmagine() { return immagine; }
    public String getUrl() { return url; }
    public String getDataScadenza() { return dataScadenza; }
    public Stato_ToDo getStato() { return stato; }
    public int getOrdine() { return ordine; }

    public void setStato(Stato_ToDo stato) { this.stato = stato; }
}
