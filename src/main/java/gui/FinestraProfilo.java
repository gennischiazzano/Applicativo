package GUI;

import javax.swing.*;
import java.awt.*;

public class FinestraProfilo extends JFrame {

    public FinestraProfilo(String username, String password) {
        setTitle("Profilo Utente");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Titolo
        JLabel titolo = new JLabel("Il tuo profilo");
        titolo.setFont(new Font("Arial", Font.BOLD, 20));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titolo, BorderLayout.NORTH);

        // Pannello con i dati
        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        infoPanel.setBackground(Color.white);

        JLabel userLabel = new JLabel("Username:");
        JLabel userValue = new JLabel(username);

        JLabel passLabel = new JLabel("Password:");
        JLabel passValue = new JLabel(password);

        infoPanel.add(userLabel);
        infoPanel.add(userValue);
        infoPanel.add(passLabel);
        infoPanel.add(passValue);

        add(infoPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
