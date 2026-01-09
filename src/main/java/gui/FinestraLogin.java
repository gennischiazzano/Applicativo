package gui;

import model.Utente;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

/**
 * Finestra di login dell'applicazione ToDo.
 * Permette all'utente di inserire username e password.
 */
public class FinestraLogin extends JFrame {

    private JTextField textField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JCheckBox rememberMe;

    public FinestraLogin() {
        FlatLightLaf.setup(); // Look moderno

        setTitle("Login");
        setSize(450, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon icon = new ImageIcon("src/main/resources/flat-lay-notebook-with-list-desk.jpg");
        JLabel colorpane = new JLabel(icon);
        colorpane.setLayout(new BorderLayout());
        setContentPane(colorpane);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false);

        // Icona utente
        JLabel iconLabel = new JLabel(new ImageIcon("img/user_icon.png"));
        iconLabel.setBounds(175, 30, 100, 100);
        mainPanel.add(iconLabel);

        // Titolo
        JLabel title = new JLabel("ToDo");
        title.setForeground(Color.white);
        title.setFont(new Font("Georgia", Font.BOLD, 44));
        title.setBounds(160, 100, 200, 50);
        mainPanel.add(title);

        // Campo username
        JLabel emailLabel = new JLabel("Username ID");
        emailLabel.setForeground(Color.white);
        emailLabel.setBounds(70, 190, 300, 20);
        mainPanel.add(emailLabel);

        textField = new JTextField();
        textField.setBounds(70, 210, 300, 30);
        mainPanel.add(textField);

        // Campo password
        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(Color.white);
        passLabel.setBounds(70, 260, 300, 20);
        mainPanel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(70, 280, 300, 30);
        mainPanel.add(passwordField);

        // Checkbox remember me
        rememberMe = new JCheckBox("Remember me");
        rememberMe.setForeground(Color.white);
        rememberMe.setOpaque(false);
        rememberMe.setBounds(70, 320, 150, 20);
        mainPanel.add(rememberMe);

        // Forgot password label
        JLabel forgotLabel = new JLabel("<HTML><U>Forgot Password?</U></HTML>");
        forgotLabel.setForeground(Color.white);
        forgotLabel.setBounds(250, 320, 150, 20);
        forgotLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mainPanel.add(forgotLabel);

        // Pulsante Login
        loginButton = new JButton("LOGIN");
        loginButton.setBounds(70, 370, 300, 40);
        loginButton.setBackground(Color.white);
        loginButton.setForeground(new Color(0x005FAD));
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        mainPanel.add(loginButton);

        // Pulsante Register
        JButton registerButton = new JButton("REGISTER");
        registerButton.setBounds(70, 420, 300, 40);
        registerButton.setBackground(Color.LIGHT_GRAY);
        registerButton.setForeground(Color.DARK_GRAY);
        mainPanel.add(registerButton);

        add(mainPanel);

        // Azione Login
        loginButton.addActionListener(e -> {
            String username = textField.getText();
            String password = new String(passwordField.getPassword());

            Utente utente = controller.Controller.login(username, password);
            if (utente != null) {
                JOptionPane.showMessageDialog(this, "Login effettuato con successo!");
                dispose();
                new SchermataPrincipale(utente);
            } else {
                JOptionPane.showMessageDialog(this, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
                textField.setText("");
                passwordField.setText("");
            }
        });

        // Azione Register
        registerButton.addActionListener(e -> {
            new FinestraRegister();
            dispose();
        });

        setVisible(true);
    }
}



