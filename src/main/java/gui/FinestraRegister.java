package GUI;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

/**
 * Finestra di registrazione dell'applicazione ToDo.
 * Permette di inserire username e password per creare un nuovo utente.
 */
public class FinestraRegister extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton confirmButton;
    private JButton backButton;

    public FinestraRegister() {
        FlatLightLaf.setup(); // Look moderno

        setTitle("Registrazione");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Sfondo con immagine
        ImageIcon icon = new ImageIcon("img/flat-lay-notebook-with-list-desk.jpg");
        JLabel background = new JLabel(icon);
        background.setLayout(new BorderLayout());
        setContentPane(background);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false);

        // Titolo
        JLabel titolo = new JLabel("Registrati a ToDo!");
        titolo.setFont(new Font("Georgia", Font.BOLD, 24));
        titolo.setForeground(Color.white);
        titolo.setBounds(110, 30, 250, 30);
        mainPanel.add(titolo);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.white);
        userLabel.setBounds(70, 100, 100, 20);
        mainPanel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(70, 125, 300, 30);
        mainPanel.add(usernameField);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.white);
        passLabel.setBounds(70, 170, 100, 20);
        mainPanel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(70, 195, 300, 30);
        mainPanel.add(passwordField);

        // Pulsanti
        confirmButton = new JButton("CONFERMA");
        confirmButton.setBounds(70, 260, 300, 40);
        confirmButton.setBackground(Color.white);
        confirmButton.setForeground(new Color(0x005FAD));
        confirmButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        mainPanel.add(confirmButton);

        backButton = new JButton("INDIETRO");
        backButton.setBounds(70, 315, 300, 40);
        backButton.setBackground(Color.LIGHT_GRAY);
        backButton.setForeground(Color.DARK_GRAY);
        mainPanel.add(backButton);

        add(mainPanel);

        // Azioni
        confirmButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Compila tutti i campi!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean registrato = controller.Controller.registraUtente(username, password);
            if (registrato) {
                JOptionPane.showMessageDialog(this, "Registrazione completata!");
                dispose();
                new FinestraLogin();
            } else {
                JOptionPane.showMessageDialog(this, "Username già esistente o errore nel database.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            new FinestraLogin();
            dispose();
        });

        setVisible(true);
    }
}
