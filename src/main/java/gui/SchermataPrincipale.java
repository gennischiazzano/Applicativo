package gui;

import model.Bacheca;
import model.Modificate;
import model.ToDo;
import model.Utente;
import com.formdev.flatlaf.FlatLightLaf;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Classe che rappresenta la schermata principale dell'applicazione ToDo.
 * Mostra l'interfaccia utente con la sidebar, le bacheche e le attività.
 */
public class SchermataPrincipale extends JFrame {

    private boolean sidebarVisible = true;
    private JPanel sidebarPanel;
    private JPanel listaBachechePanel;
    private JPanel pannelloBacheche;
    private JPanel pannelloCentrale;
    private Utente utente;

    /**
     * Costruttore della schermata principale.
     * Inizializza l'interfaccia grafica e carica le bacheche dell'utente.
     *
     * @param utente l'utente corrente
     */
    public SchermataPrincipale(Utente utente) {
        FlatLightLaf.setup();
        this.utente = utente;

        setTitle("ToDo - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Color darkBlue = new Color(10, 25, 47);
        Color panelColor = new Color(20, 40, 70);

        JPanel overlayPanel = new JPanel(new BorderLayout());
        overlayPanel.setBackground(darkBlue);
        setContentPane(overlayPanel);

        // HEADER
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(panelColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.setOpaque(false);

        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftHeader.setOpaque(false);
        leftHeader.setBackground(panelColor);
        JButton toggleMenu = new JButton("\u25C0");
        toggleMenu.setFocusPainted(false);
        toggleMenu.setBorderPainted(false);
        toggleMenu.setContentAreaFilled(false);
        toggleMenu.setForeground(Color.WHITE);
        toggleMenu.setFont(new Font("SansSerif", Font.PLAIN, 18));
        toggleMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel titleLabel = new JLabel("TODO");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 24));
        leftHeader.add(toggleMenu);
        leftHeader.add(titleLabel);

        JPanel centerHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        centerHeader.setOpaque(false);
        centerHeader.setBackground(panelColor);
        JTextField searchField = new JTextField(30);
        searchField.setBackground(new Color(30, 50, 80));
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        JButton createButton = new JButton("Crea Bacheca");
        createButton.setBackground(new Color(40, 60, 90));
        createButton.setForeground(Color.WHITE);
        createButton.setFocusPainted(false);
        createButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        createButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        centerHeader.add(searchField);
        centerHeader.add(createButton);

        JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightHeader.setOpaque(false);
        rightHeader.setBackground(panelColor);

        JButton profileButton = new JButton(new ImageIcon("img/profilo.jpg"));
        profileButton.setPreferredSize(new Dimension(40, 40));
        profileButton.setFocusPainted(false);
        profileButton.setBorderPainted(false);
        profileButton.setContentAreaFilled(false);
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profileButton.addActionListener(e -> new FinestraProfilo(utente.getUsername(), utente.getPassword()));

        // Nuovo pulsante per ToDo Condivisi
        JButton sharedTodosButton = new JButton("ToDo Condivisi");
        sharedTodosButton.setBackground(new Color(50, 90, 120));
        sharedTodosButton.setForeground(Color.WHITE);
        sharedTodosButton.setFocusPainted(false);
        sharedTodosButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        sharedTodosButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sharedTodosButton.addActionListener(e -> new FinestraMostraCondivisi(utente.getUsername()));

        rightHeader.add(sharedTodosButton);
        rightHeader.add(profileButton);

        headerPanel.add(leftHeader, BorderLayout.WEST);
        headerPanel.add(centerHeader, BorderLayout.CENTER);
        headerPanel.add(rightHeader, BorderLayout.EAST);
        overlayPanel.add(headerPanel, BorderLayout.NORTH);

        // SIDEBAR
        sidebarPanel = new JPanel(new BorderLayout());
        sidebarPanel.setBackground(panelColor);
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));
        listaBachechePanel = new JPanel();
        listaBachechePanel.setLayout(new BoxLayout(listaBachechePanel, BoxLayout.Y_AXIS));
        listaBachechePanel.setBackground(panelColor);
        JScrollPane scrollSidebar = new JScrollPane(listaBachechePanel);
        scrollSidebar.getViewport().setBackground(panelColor);
        scrollSidebar.setBorder(null);
        sidebarPanel.add(scrollSidebar, BorderLayout.CENTER);
        overlayPanel.add(sidebarPanel, BorderLayout.WEST);

        // CENTRO
        ImageIcon centerBackgroundImage = new ImageIcon("img/1_fseafaww1.jpg");
        JLabel centerBackgroundLabel = new JLabel(centerBackgroundImage);
        centerBackgroundLabel.setLayout(new BorderLayout());

        pannelloBacheche = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        pannelloBacheche.setOpaque(false);
        JScrollPane scrollCenter = new JScrollPane(pannelloBacheche);
        scrollCenter.setOpaque(false);
        scrollCenter.getViewport().setOpaque(false);
        scrollCenter.setBorder(null);

        pannelloCentrale = new JPanel(new BorderLayout());
        pannelloCentrale.setOpaque(false);
        pannelloCentrale.add(scrollCenter, BorderLayout.CENTER);
        centerBackgroundLabel.add(pannelloCentrale, BorderLayout.CENTER);

        overlayPanel.add(centerBackgroundLabel, BorderLayout.CENTER);

        // Azioni pulsanti
        toggleMenu.addActionListener(e -> {
            sidebarVisible = !sidebarVisible;
            sidebarPanel.setVisible(sidebarVisible);
            toggleMenu.setText(sidebarVisible ? "\u25C0" : "\u25B6");
            revalidate();
            repaint();
        });

        createButton.addActionListener(e -> new FinestraCreaBacheca(this));

        caricaBacheche();

        setVisible(true);
    }

    /**
     * Aggiunge una bacheca alla lista delle bacheche visualizzate.
     *
     * @param idBacheca     l'id della bacheca
     * @param titoloBacheca  il titolo della bacheca
     */
    public void aggiungiBacheca(int idBacheca, String titoloBacheca) {
        Color cardColor = new Color(20, 40, 70);
        Color textColor = Color.WHITE;

        JLabel nome = new JLabel(titoloBacheca);
        nome.setForeground(textColor);
        listaBachechePanel.add(nome);

        JPanel card = new JPanel();
        card.setOpaque(false);
        card.setPreferredSize(new Dimension(200, 150));
        card.setBackground(cardColor);
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        card.setLayout(new BorderLayout());

        JLabel titoloLabel = new JLabel(titoloBacheca, SwingConstants.CENTER);
        titoloLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        titoloLabel.setForeground(textColor);
        card.add(titoloLabel, BorderLayout.CENTER);

        // Pulsanti
        JButton azioneButton = new JButton("Crea Attività");
        azioneButton.setFocusPainted(false);
        azioneButton.setFont(new Font("Georgia", Font.PLAIN, 12));
        azioneButton.setBackground(new Color(40, 60, 90));
        azioneButton.setForeground(Color.WHITE);
        azioneButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JButton modificaBachecaBtn = new JButton("Modifica descrizione");
        modificaBachecaBtn.setFocusPainted(false);
        modificaBachecaBtn.setFont(new Font("Georgia", Font.PLAIN, 12));
        modificaBachecaBtn.setBackground(new Color(40, 60, 90));
        modificaBachecaBtn.setForeground(Color.WHITE);
        modificaBachecaBtn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JButton eliminaBachecaBtn = new JButton("Elimina Bacheca");
        eliminaBachecaBtn.setFocusPainted(false);
        eliminaBachecaBtn.setFont(new Font("Georgia", Font.PLAIN, 12));
        eliminaBachecaBtn.setBackground(new Color(90, 30, 30));
        eliminaBachecaBtn.setForeground(Color.WHITE);
        eliminaBachecaBtn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Pannello pulsanti
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 5));
        buttonPanel.setOpaque(false);
        buttonPanel.add(azioneButton);
        buttonPanel.add(modificaBachecaBtn);
        buttonPanel.add(eliminaBachecaBtn);

        card.add(buttonPanel, BorderLayout.SOUTH);

        // Azioni pulsanti
        azioneButton.addActionListener(e -> {
            List<ToDo> toDoBacheca = controller.Controller.getToDoByBacheca(idBacheca);
            new FinestraCreaAttivita(toDoBacheca, idBacheca);
        });

        modificaBachecaBtn.addActionListener(e -> {
            // Recupera la descrizione corrente
            String descrizioneAttuale = Controller.getDescrizioneBacheca(idBacheca);

            // Pannello personalizzato per input e label descrizione attuale
            JPanel panel = new JPanel(new BorderLayout(5, 5));
            JTextField inputField = new JTextField();
            JLabel currentLabel = new JLabel("Descrizione attuale: " + descrizioneAttuale);
            currentLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            currentLabel.setForeground(Color.DARK_GRAY);

            panel.add(inputField, BorderLayout.NORTH);
            panel.add(currentLabel, BorderLayout.SOUTH);

            int result = JOptionPane.showConfirmDialog(this, panel, "Modifica descrizione",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String nuovaDescrizione = inputField.getText().trim();
                if (!nuovaDescrizione.isEmpty()) {
                    boolean ok = Controller.modificaDescrizioneBacheca(idBacheca, nuovaDescrizione);
                    if (ok) {
                        JOptionPane.showMessageDialog(this, "Descrizione aggiornata!");
                        Bacheca bacheca = new Bacheca();
                        bacheca.getOperazioniSuBacheca().add(new Modificate(true));
                        caricaBacheche();
                    } else {
                        JOptionPane.showMessageDialog(this, "Errore durante la modifica.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        eliminaBachecaBtn.addActionListener(e -> {
            int conferma = JOptionPane.showConfirmDialog(
                    this,
                    "Vuoi davvero eliminare questa bacheca?",
                    "Conferma eliminazione",
                    JOptionPane.YES_NO_OPTION
            );

            if (conferma == JOptionPane.YES_OPTION) {
                boolean eliminata = Controller.eliminaBacheca(idBacheca);
                if (eliminata) {
                    JOptionPane.showMessageDialog(this, "Bacheca eliminata con successo!");
                    Bacheca bacheca = new Bacheca();
                    bacheca.getOperazioniSuBacheca().add(new Model.Eliminate(true));
                    caricaBacheche();
                } else {
                    JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Click su card per aprire attività
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() != azioneButton) {
                    List<ToDo> toDoBacheca = Controller.getToDoByBacheca(idBacheca);
                    List<ToDo> toDoCondivisi = Controller.getToDoCondivisi(utente.getUsername());

                    if (!toDoBacheca.isEmpty() || !toDoCondivisi.isEmpty()) {
                        new FinestraMostraAttivita(idBacheca, utente.getUsername());
                    } else {
                        JOptionPane.showMessageDialog(SchermataPrincipale.this,
                                "Non ci sono attività in questa bacheca.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        pannelloBacheche.add(card);
        pannelloBacheche.revalidate();
        pannelloBacheche.repaint();
        listaBachechePanel.revalidate();
        listaBachechePanel.repaint();
    }

    /**
     * Carica le bacheche dell'utente e le visualizza nell'interfaccia.
     */
    public void caricaBacheche() {
        pannelloBacheche.removeAll();
        listaBachechePanel.removeAll();

        List<Bacheca> bachecheUtente = controller.Controller.getBacheche(utente.getUsername()); // <<< qui
        for (Bacheca b : bachecheUtente) {
            aggiungiBacheca(b.getId(), b.getTitolo());
        }

        pannelloBacheche.revalidate();
        pannelloBacheche.repaint();
        listaBachechePanel.revalidate();
        listaBachechePanel.repaint();
    }

    /**
     * Restituisce lo username dell'utente corrente.
     *
     * @return lo username dell'utente
     */
    public String getUtenteUsername() {
        return utente.getUsername();
    }

}


