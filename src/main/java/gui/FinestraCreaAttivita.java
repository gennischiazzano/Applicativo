package gui;

import Model.ToDo;
import Model.Stato_ToDo;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Finestra per creare un nuovo ToDo in una bacheca.
 * L'utente può inserire titolo, descrizione, URL e data di scadenza.
 */
public class FinestraCreaAttivita extends JFrame {

    private JTextField titoloField;
    private JTextArea descrizioneArea;
    private JTextField urlField;
    private JTextField scadenzaField;

    private int bachecaID;
    private List<ToDo> listaToDo;

    /**
     * Costruttore che inizializza la finestra e i campi di input.
     * @param listaToDo lista dei ToDo già presenti nella bacheca
     * @param bachecaID ID della bacheca dove aggiungere il nuovo ToDo
     */
    public FinestraCreaAttivita(List<ToDo> listaToDo, int bachecaID) {
        this.listaToDo = listaToDo;
        this.bachecaID = bachecaID;

        setTitle("Crea Attività");
        setSize(400, 350);
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
        titoloField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(titoloField, gbc);

        // Data Scadenza
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Data Scadenza (YYYY-MM-DD):"), gbc);
        scadenzaField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(scadenzaField, gbc);

        // Descrizione
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        add(new JLabel("Descrizione:"), gbc);
        descrizioneArea = new JTextArea(4, 20);
        descrizioneArea.setLineWrap(true);
        descrizioneArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descrizioneArea);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // URL
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("URL Immagine:"), gbc);
        urlField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(urlField, gbc);

        // Pulsante crea
        JButton creaButton = new JButton("Crea");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(creaButton, gbc);

        creaButton.addActionListener((ActionEvent e) -> {
            String titolo = titoloField.getText().trim();
            String descrizione = descrizioneArea.getText().trim();
            String url = urlField.getText().trim();
            String dataScadenzaStr = scadenzaField.getText().trim();

            if(titolo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Il campo titolo non può essere vuoto!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate dataScadenza = null;
            if(!dataScadenzaStr.isEmpty()) {
                try {
                    dataScadenza = LocalDate.parse(dataScadenzaStr);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Formato data non valido. Usa YYYY-MM-DD.", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            int ordine = listaToDo.size() + 1;

            ToDo nuovo = new ToDo(
                    titolo,
                    descrizione,
                    null,
                    url,
                    dataScadenza != null ? dataScadenza.toString() : null,
                    Stato_ToDo.NonCompletato,
                    ordine,
                    bachecaID
            );

            boolean ok = Controller.creaToDo(nuovo, bachecaID);

            if(ok) {
                listaToDo.add(nuovo);
                JOptionPane.showMessageDialog(this, "Attività creata con successo!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante la creazione dell'attività.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}

