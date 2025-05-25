package GUI;

import Model.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraLogin extends JFrame {
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private Utente utente;

    public FinestraLogin() {

        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Impedisce il ridimensionamento
        setLayout(new BorderLayout());

        // Pannello centrale con i campi login
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.setBackground(Color.white);

        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(userLabel, gbc);

        textField = new JTextField(15);
        gbc.gridx = 1;
        centerPanel.add(textField, gbc);

        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        centerPanel.add(passwordField, gbc);

        JLabel titolo = new JLabel("Welcome in ToDo!");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        titolo.setBackground(Color.white);

        add(titolo, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        // Footer con Login e Register uno accanto all'altro
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // spazio tra i bottoni
        bottomPanel.add(loginButton);
        bottomPanel.add(registerButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Azione login
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword());
                Utente utente = new Utente(username, password);
                if(utente.getUsername().equals(username) && utente.getUsername().equals(password)){
                    JOptionPane.showMessageDialog(FinestraLogin.this, "Login effettuato con successo");
                    dispose();
                    new SchermataPrincipale(utente);
                } else {
                    JOptionPane.showMessageDialog(FinestraLogin.this, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
                    textField.setText("");
                    passwordField.setText("");
                }
            }
        });
        registerButton.addActionListener(e->{
            new FinestraRegister();
            FinestraLogin.this.dispose();
        });

        setVisible(true);
    }
}
