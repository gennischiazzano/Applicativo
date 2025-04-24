import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ToDo {
    //Attributi
    private String titolo;
    private LocalDate dataScadenza;
    private String descrizione;
    private String immagine;
    private String url;
    private Stato_ToDo stato;
    private int ordine;
    private List<Operazione> OperazioneSuToDO;
    //Costruttore

    public ToDo(String titolo, LocalDate dataScadenza, String url, String descrizione, String immagine, Stato_ToDo stato, int ordine,List<Operazione> OperazioneSuToDO) {

        this.titolo = titolo;
        this.dataScadenza = dataScadenza;
        this.descrizione = descrizione;
        this.immagine = immagine;
        this.url = url;
        this.stato = stato;
        this.ordine = ordine;
        this.OperazioneSuToDO = new ArrayList<>();

    }
}
