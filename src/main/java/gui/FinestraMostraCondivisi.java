package gui;

import model.ToDo;
import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Finestra che mostra tutti i ToDo condivisi con l'utente.
 * L'utente può solo visualizzarli, senza modificarli.
 */
public class FinestraMostraCondivisi extends JFrame {

    private JPanel panel;
    private String username;

    /**
     * Costruttore che inizializza la finestra e carica i ToDo condivisi.
     * @param username nome utente corrente
     */
    public FinestraMostraCondivisi(String username) {
        this.username = username;

        setTitle("ToDo Condivisi con te");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        caricaToDoCondivisi();
        setVisible(true);
    }

    /** Carica tutti i ToDo condivisi con l'utente e li mostra nella finestra. */
    private void caricaToDoCondivisi() {
        panel.removeAll();
        List<ToDo> listaCondivisi = Controller.getToDoCondivisi(username);

        if (listaCondivisi.isEmpty()) {
            JLabel emptyLabel = new JLabel("Non ci sono ToDo condivisi.");
            emptyLabel.setForeground(Color.WHITE);
            panel.add(emptyLabel);
        } else {
            for (ToDo todo : listaCondivisi) {
                JCheckBox checkBox = new JCheckBox(
                        todo.getOrdine() + ". " + todo.getTitolo() + " [" + todo.getStato() + "]",
                        todo.getStato() == Model.Stato_ToDo.Completato
                );
                checkBox.setToolTipText("Descrizione: " + todo.getDescrizione() + "\nScadenza: " + todo.getDataScadenza());
                checkBox.setEnabled(false); // i ToDo condivisi non possono essere modificati
                panel.add(checkBox);
            }
        }

        panel.revalidate();
        panel.repaint();
    }

}

