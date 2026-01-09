package GUI;

import Model.Bacheca_Titolo;
import javax.swing.*;
import java.awt.*;

/**
 * Finestra per creare una nuova bacheca.
 * Permette all'utente di selezionare il titolo e inserire la descrizione.
 */
public class FinestraCreaBacheca extends JFrame {

    private JComboBox<Bacheca_Titolo> titoloComboBox;
    private JTextArea descrizioneArea;
    private JButton confermaButton;
    private SchermataPrincipale schermataPrincipale;

    public FinestraCreaBacheca(SchermataPrincipale schermataPrincipale) {
        this.schermataPrincipale = schermataPrincipale;

        setTitle("Crea nuova bacheca");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Titolo bacheca
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
        centerPanel.add(new JScrollPane(descrizioneArea), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Pulsante conferma
        confermaButton = new JButton("Conferma");
        add(confermaButton, BorderLayout.SOUTH);

        confermaButton.addActionListener(e -> {
            Bacheca_Titolo titolo = (Bacheca_Titolo) titoloComboBox.getSelectedItem();
            String descrizione = descrizioneArea.getText().trim();

            if (descrizione.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserisci una descrizione!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String utenteUsername = schermataPrincipale.getUtenteUsername();
            boolean successo = controller.Controller.creaBacheca(titolo, descrizione, utenteUsername);

            if (successo) {
                JOptionPane.showMessageDialog(this,
                        "Bacheca creata:\nTitolo: " + titolo + "\nDescrizione: " + descrizione,
                        "Successo", JOptionPane.INFORMATION_MESSAGE);
                schermataPrincipale.caricaBacheche();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Non è stato possibile creare la bacheca.\nHai già raggiunto il massimo (3) o una bacheca con questo titolo esiste.",
                        "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
