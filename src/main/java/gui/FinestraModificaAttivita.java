package gui;

import model.ToDo;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * Finestra per modificare un ToDo esistente.
 * Permette di aggiornare titolo, descrizione, URL immagine e data di scadenza.
 */
public class FinestraModificaAttivita extends JFrame {

    private JTextField titoloField;
    private JTextArea descrizioneArea;
    private JTextField urlField;
    private JTextField scadenzaField;

    private ToDo todo;
    private int bachecaID;
    private FinestraMostraAttivita finestraPadre;

    /**
     * Costruttore che inizializza la finestra con i dati del ToDo da modificare.
     * @param todo ToDo da modificare
     * @param bachecaID ID della bacheca del ToDo
     * @param finestraPadre finestra che contiene la lista ToDo, per aggiornare la visualizzazione
     */
    public FinestraModificaAttivita(ToDo todo, int bachecaID, FinestraMostraAttivita finestraPadre) {
        this.todo = todo;
        this.bachecaID = bachecaID;
        this.finestraPadre = finestraPadre;

        setTitle("Modifica Attività");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Titolo
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Titolo:"), gbc);
        titoloField = new JTextField(todo.getTitolo());
        gbc.gridx = 1;
        add(titoloField, gbc);

        // Data Scadenza
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Data Scadenza (YYYY-MM-DD):"), gbc);
        scadenzaField = new JTextField(todo.getDataScadenza());
        gbc.gridx = 1;
        add(scadenzaField, gbc);

        // Descrizione
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        add(new JLabel("Descrizione:"), gbc);
        descrizioneArea = new JTextArea(todo.getDescrizione(), 4, 20);
        descrizioneArea.setLineWrap(true);
        descrizioneArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descrizioneArea);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // URL
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("URL Immagine:"), gbc);
        urlField = new JTextField(todo.getUrl());
        gbc.gridx = 1;
        add(urlField, gbc);

        // Pulsante Salva
        JButton salvaButton = new JButton("Salva Modifiche");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(salvaButton, gbc);

        salvaButton.addActionListener(e -> {
            String titolo = titoloField.getText().trim();
            String descrizione = descrizioneArea.getText().trim();
            String url = urlField.getText().trim();
            String dataScadenzaStr = scadenzaField.getText().trim();

            if (titolo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Il campo titolo non può essere vuoto!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String dataScadenza = null;
            if (!dataScadenzaStr.isEmpty()) {
                try {
                    LocalDate.parse(dataScadenzaStr);
                    dataScadenza = dataScadenzaStr;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Formato data non valido. Usa YYYY-MM-DD.", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            todo.setTitolo(titolo);
            todo.setDescrizione(descrizione);
            todo.setUrl(url);
            todo.setDataScadenza(dataScadenza);

            boolean ok = Controller.modificaToDo(todo, bachecaID);
            if (ok) {
                JOptionPane.showMessageDialog(this, "ToDo modificato con successo!");
                if (finestraPadre != null) finestraPadre.refresh();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante la modifica.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

}

