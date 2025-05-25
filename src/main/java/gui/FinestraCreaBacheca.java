package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Model.Bacheca_Titolo;
import Model.ToDo;

public class FinestraCreaBacheca extends JFrame {

    private JComboBox<Bacheca_Titolo> titoloComboBox;
    private JTextArea descrizioneArea;
    private JButton confermaButton;
    private List<ToDo> lista = new ArrayList<>();

    // Aggiunto riferimento alla schermata principale
    private SchermataPrincipale schermataPrincipale;

    public FinestraCreaBacheca(SchermataPrincipale schermataPrincipale) {
        this.schermataPrincipale = schermataPrincipale; // Salvo il riferimento

        setTitle("Crea nuova bacheca");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Titolo della bacheca
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Titolo bacheca:"));
        titoloComboBox = new JComboBox<>(Bacheca_Titolo.values());
        topPanel.add(titoloComboBox);
        add(topPanel, BorderLayout.NORTH);

        // Descrizione
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(new JLabel("Descrizione:"), BorderLayout.NORTH);
        descrizioneArea = new JTextArea(5, 30);
        descrizioneArea.setLineWrap(true);
        descrizioneArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descrizioneArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Pulsante
        confermaButton = new JButton("Conferma");
        add(confermaButton, BorderLayout.SOUTH);

        // Azione
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bacheca_Titolo titolo = (Bacheca_Titolo) titoloComboBox.getSelectedItem();
                String descrizione = descrizioneArea.getText();

                if (descrizione.isBlank()) {
                    JOptionPane.showMessageDialog(FinestraCreaBacheca.this, "Inserisci una descrizione!", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Aggiungo la bacheca alla schermata principale
                schermataPrincipale.aggiungiBacheca(titolo.name());

                JOptionPane.showMessageDialog(FinestraCreaBacheca.this,
                        "Bacheca creata:\nTitolo: " + titolo + "\nDescrizione: " + descrizione,
                        "Successo", JOptionPane.INFORMATION_MESSAGE);

                dispose(); // Chiudo finestra
            }
        });

        setVisible(true);
    }
}
