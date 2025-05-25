package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Utente;

public class FinestraRegister extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton confirmButton;
    private JButton backButton;
    private Utente utente;

    public FinestraRegister() {

        setTitle("Registrazione");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Non chiude tutto il programma
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Titolo in alto
        JLabel titolo = new JLabel("Registrati a ToDo!");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        titolo.setBackground(Color.white);
        titolo.setOpaque(true);
        add(titolo, BorderLayout.NORTH);

        // Pannello centrale
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(userLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        centerPanel.add(usernameField, gbc);

        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        centerPanel.add(passwordField, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Pannello inferiore con i pulsanti
        confirmButton = new JButton("Conferma");
        backButton = new JButton("Indietro");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.add(confirmButton);
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Azioni
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                Utente utente = new Utente(username, password);
                if (!username.isEmpty() && !password.isEmpty()) {
                    JOptionPane.showMessageDialog(FinestraRegister.this, "Registrazione completata!");
                    dispose(); // Chiude la finestra di registrazione
                    new FinestraLogin();
                } else {
                    JOptionPane.showMessageDialog(FinestraRegister.this, "Compila tutti i campi!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> {
            new FinestraLogin();
            dispose();
        }); // Torna indietro
        setVisible(true);
    }
}
