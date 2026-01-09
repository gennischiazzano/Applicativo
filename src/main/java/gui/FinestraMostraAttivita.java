package gui;

import model.ToDo;
import model.Stato_ToDo;
import model.Eliminate;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Finestra che mostra tutte le attività di una bacheca.
 * Permette di modificare, eliminare o condividere ciascun ToDo.
 */
public class FinestraMostraAttivita extends JFrame {

    private int idBacheca;
    private JPanel panel;
    private String username;

    /**
     * Costruttore che inizializza la finestra con i ToDo della bacheca.
     * @param idBacheca ID della bacheca
     * @param username username dell'utente corrente
     */
    public FinestraMostraAttivita(int idBacheca, String username) {
        this.idBacheca = idBacheca;
        this.username = username;

        setTitle("Le mie attività");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        refresh();
        setVisible(true);
    }

    /**
     * Aggiorna la lista dei ToDo nella finestra.
     * Recupera i ToDo tramite il Controller e costruisce i componenti grafici.
     */
    public void refresh() {
        panel.removeAll();
        List<ToDo> toDoBacheca = Controller.getToDoByBacheca(idBacheca);

        if (toDoBacheca.isEmpty()) {
            JLabel emptyLabel = new JLabel("Non ci sono attività in questa bacheca.");
            emptyLabel.setForeground(Color.WHITE);
            panel.add(emptyLabel);
        } else {
            for (ToDo todo : toDoBacheca) {
                JCheckBox checkBox = new JCheckBox(
                        todo.getOrdine() + ". " + todo.getTitolo() + " [" + todo.getStato() + "]",
                        todo.getStato() == Stato_ToDo.Completato
                );
                checkBox.setToolTipText("Descrizione: " + todo.getDescrizione() + "\nScadenza: " + todo.getDataScadenza());

                checkBox.addActionListener(e -> {
                    todo.setStato(checkBox.isSelected() ? Stato_ToDo.Completato : Stato_ToDo.NonCompletato);
                    boolean aggiornato = Controller.aggiornaStatoToDo(todo, idBacheca);
                    if (!aggiornato) {
                        JOptionPane.showMessageDialog(this, "Errore aggiornamento stato ToDo.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                    checkBox.setText(todo.getOrdine() + ". " + todo.getTitolo() + " [" + todo.getStato() + "]");
                });

                JButton modificaBtn = new JButton("Modifica");
                modificaBtn.addActionListener((ActionEvent e) -> {
                    new FinestraModificaAttivita(todo, idBacheca, this);
                });

                JButton eliminaBtn = new JButton("Elimina");
                eliminaBtn.addActionListener(e -> {
                    int conferma = JOptionPane.showConfirmDialog(this,
                            "Vuoi davvero eliminare questo ToDo?",
                            "Conferma eliminazione",
                            JOptionPane.YES_NO_OPTION);
                    if (conferma == JOptionPane.YES_OPTION) {
                        boolean eliminato = Controller.eliminaToDo(todo, idBacheca);
                        if (eliminato) {
                            todo.getOperazioniSuToDo().add(new Eliminate(true));
                            refresh();
                        } else {
                            JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione.", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                JButton condividiBtn = new JButton("Condividi");
                condividiBtn.addActionListener(e -> {
                    String destinatario = JOptionPane.showInputDialog(this, "Inserisci username con cui condividere:");
                    if (destinatario != null && !destinatario.trim().isEmpty()) {
                        destinatario = destinatario.trim();
                        if (destinatario.equalsIgnoreCase(username)) {
                            JOptionPane.showMessageDialog(this, "Non puoi condividere un ToDo con te stesso!", "Errore", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        boolean ok = Controller.condividiToDo(todo.getId(), destinatario);
                        if (ok) {
                            JOptionPane.showMessageDialog(this, "ToDo condiviso con " + destinatario + "!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Errore nella condivisione.", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                JPanel todoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                todoPanel.add(checkBox);
                todoPanel.add(modificaBtn);
                todoPanel.add(eliminaBtn);
                todoPanel.add(condividiBtn);

                panel.add(todoPanel);
            }
        }

        panel.revalidate();
        panel.repaint();
    }
}


