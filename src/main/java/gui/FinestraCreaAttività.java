package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import Model.Stato_ToDo;
import Model.ToDo;

public class FinestraCreaAttività extends JFrame {

    private JTextField titoloField;
    private JTextArea descrizioneArea;
    private JTextField immagineField;
    private JTextField urlField;
    private JTextField scadenzaField;
    private JComboBox<Stato_ToDo> statoCombo;

    public FinestraCreaAttività(List<ToDo> listaToDo) {
        setTitle("Crea Attività");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        add(new JLabel("Titolo:"));
        titoloField = new JTextField();
        add(titoloField);

        add(new JLabel("Descrizione:"));
        descrizioneArea = new JTextArea(3, 20);
        add(new JScrollPane(descrizioneArea));

        add(new JLabel("Immagine (percorso):"));
        immagineField = new JTextField();
        add(immagineField);

        add(new JLabel("URL:"));
        urlField = new JTextField();
        add(urlField);

        add(new JLabel("Data Scadenza:"));
        scadenzaField = new JTextField();
        add(scadenzaField);

        add(new JLabel("Stato:"));
        statoCombo = new JComboBox<>(Stato_ToDo.values());
        add(statoCombo);

        JButton creaButton = new JButton("Crea");
        add(new JLabel()); // vuoto
        add(creaButton);

        creaButton.addActionListener((ActionEvent e) -> {
            ToDo nuovo = new ToDo(
                    titoloField.getText(),
                    descrizioneArea.getText(),
                    immagineField.getText(),
                    urlField.getText(),
                    scadenzaField.getText(),
                    (Stato_ToDo) statoCombo.getSelectedItem(),
                    listaToDo.size() + 1
            );
            listaToDo.add(nuovo);
            if(!titoloField.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Attività creata con successo!");
                dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Il campo titolo non può essere vuoto!");
            }

        });

        setVisible(true);
    }
}
