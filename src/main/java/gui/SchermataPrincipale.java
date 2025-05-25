package GUI;

import Model.ToDo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import Model.Utente;

public class SchermataPrincipale extends JFrame {
    private boolean sidebarVisible = true;
    private JPanel sidebarPanel;
    private JPanel listaBachechePanel;
    private List<String> bacheche;
    private JPanel pannelloBacheche;
    private JPanel pannelloCentrale;
    private List<ToDo> listaToDo;
    private Utente utente;
    private JButton toggleMenu;

    public SchermataPrincipale(Utente utente) {

        setTitle("ToDo - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        bacheche = new ArrayList<>();
        listaToDo = new ArrayList<>();

        // --------- HEADER ---------
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(245, 245, 245));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // SINISTRA
        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftHeader.setBackground(new Color(245, 245, 245));
        toggleMenu = new JButton("\u25C0"); // freccia per chiudere inizialmente
        toggleMenu.setFocusPainted(false);
        toggleMenu.setBorderPainted(false);
        toggleMenu.setContentAreaFilled(false);
        toggleMenu.setFont(new Font("SansSerif", Font.PLAIN, 18));
        toggleMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titleLabel = new JLabel("TODO");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        leftHeader.add(toggleMenu);
        leftHeader.add(titleLabel);

        // CENTRO
        JPanel centerHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        centerHeader.setBackground(new Color(245, 245, 245));
        JTextField searchField = new JTextField(20);
        JButton createButton = new JButton("Crea");
        centerHeader.add(searchField);
        centerHeader.add(createButton);

        // DESTRA
        JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightHeader.setBackground(new Color(245, 245, 245));
        JLabel profileIcon = new JLabel();

        JButton profileButton = new JButton(new ImageIcon("img/profilo.jpg"));
        profileButton.setPreferredSize(new Dimension(40, 40));
        profileButton.setFocusPainted(false);
        profileButton.setBorderPainted(false);
        profileButton.setContentAreaFilled(false);
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profileButton.addActionListener(e -> {
            new FinestraProfilo(utente.getUsername(), utente.getPassword());
        });

        rightHeader.add(profileButton);
        rightHeader.add(profileIcon);

        headerPanel.add(leftHeader, BorderLayout.WEST);
        headerPanel.add(centerHeader, BorderLayout.CENTER);
        headerPanel.add(rightHeader, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // --------- SIDEBAR ---------
        sidebarPanel = new JPanel(new BorderLayout());
        sidebarPanel.setBackground(new Color(220, 220, 220));
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));
        listaBachechePanel = new JPanel();
        listaBachechePanel.setLayout(new BoxLayout(listaBachechePanel, BoxLayout.Y_AXIS));
        listaBachechePanel.setBackground(new Color(220, 220, 220));
        JScrollPane scrollSidebar = new JScrollPane(listaBachechePanel);
        sidebarPanel.add(scrollSidebar, BorderLayout.CENTER);
        add(sidebarPanel, BorderLayout.WEST);

        // --------- CENTRO: pannello bacheche visibili ---------
        pannelloBacheche = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        pannelloBacheche.setBackground(Color.WHITE);
        JScrollPane scrollCenter = new JScrollPane(pannelloBacheche);
        pannelloCentrale = new JPanel(new BorderLayout());
        pannelloCentrale.add(scrollCenter, BorderLayout.CENTER);
        add(pannelloCentrale, BorderLayout.CENTER);

        // --------- ACTION LISTENERS ---------
        toggleMenu.addActionListener(e -> {
            sidebarVisible = !sidebarVisible;
            sidebarPanel.setVisible(sidebarVisible);
            toggleMenu.setText(sidebarVisible ? "\u25C0" : "\u25B6");
            revalidate();
            repaint();
        });

        createButton.addActionListener(e -> {
            new FinestraCreaBacheca(this);
        });

        setVisible(true);
    }

    public void aggiungiBacheca(String titoloBacheca) {
        bacheche.add(titoloBacheca);

        JLabel nome = new JLabel(titoloBacheca);
        listaBachechePanel.add(nome);

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(200, 150));
        card.setBackground(new Color(230, 230, 250));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        card.setLayout(new BorderLayout());

        JLabel titoloLabel = new JLabel(titoloBacheca, SwingConstants.CENTER);
        titoloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        card.add(titoloLabel, BorderLayout.CENTER);

        JButton azioneButton = new JButton("Crea Attività");
        azioneButton.setFocusPainted(false);
        azioneButton.setFont(new Font("Arial", Font.PLAIN, 12));
        card.add(azioneButton, BorderLayout.SOUTH);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() != azioneButton) {
                    new FinestraMostraAttività(listaToDo);
                }
            }
        });

        azioneButton.addActionListener(e -> {
            new FinestraCreaAttività(listaToDo);
        });

        pannelloBacheche.add(card);
        pannelloBacheche.revalidate();
        pannelloBacheche.repaint();
        listaBachechePanel.revalidate();
        listaBachechePanel.repaint();
    }
}
