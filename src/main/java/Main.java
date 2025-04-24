import java.time.LocalDate;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        Utente utente = new Utente("Pippo","rossi");
        Utente utente2 = new Utente("Pluto","grigio");

        Bacheca bacheca = new Bacheca(Bacheca_Titolo.Università, "Attività inerente all'università da svolgere",new ArrayList<>());

        ToDo Esame = new ToDo(
               "Consegna",
               LocalDate.of(2025, 5  , 21),
               "https://ciao.com",
               "esame oo",
               "java.png",
               Stato_ToDo.Completato,
               1,
               new ArrayList<>()
        );
    }
}