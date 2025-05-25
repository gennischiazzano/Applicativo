package GUI;

import javax.swing.*;
import java.util.List;
import Model.ToDo;

public class FinestraMostraAttività extends JFrame {

    public FinestraMostraAttività(List<ToDo> listaToDo) {
        setTitle("Le mie attività");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (ToDo todo : listaToDo) {
            JCheckBox checkBox = new JCheckBox(todo.getOrdine() + ". " + todo.getTitolo() + " [" + todo.getStato() + "]");
            checkBox.setToolTipText("Descrizione: " + todo.getDescrizione() + "\nScadenza: " + todo.getDataScadenza());
            panel.add(checkBox);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setVisible(true);
    }
}
