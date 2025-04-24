import java.util.ArrayList;
import java.util.List;

public class Bacheca {
    private Bacheca_Titolo titolo;
    private String descrizione;
    private List<Operazione> OperazioneSuBacheca;

    public Bacheca(Bacheca_Titolo titolo, String descrizione, List<Operazione> OperazioneSuBacheca) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.OperazioneSuBacheca = new ArrayList<>();
    }
}
