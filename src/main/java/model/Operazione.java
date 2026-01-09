package model;

/** Classe base per le operazioni su un ToDo (Create, Modificate, Eliminate). */
public class Operazione {
    protected boolean Stato_Op;

    /**
     * Costruttore Operazione.
     * @param Stato_Op indica se l’operazione è avvenuta con successo
     */
    public Operazione(boolean Stato_Op){
        this.Stato_Op = Stato_Op;
    }

    /** Restituisce lo stato dell’operazione. */
    public boolean getStato_Op() {
        return Stato_Op;
    }

    /** Imposta lo stato dell’operazione. */
    public void setStato_Op(boolean Stato_Op) {
        this.Stato_Op = Stato_Op;
    }
}

