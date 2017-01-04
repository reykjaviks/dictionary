package britionary.gui;

import britionary.logic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener {

    private Container container;
    private JTextField sField;
    private JTextField pField;
    private JButton button;
    private Searcher searcher = Searcher.getInstance();

    public void buildGUI() {

        this.setLayout(new FlowLayout());
        sField = new JTextField("", 20);
        pField = new JTextField("", 20);
        button = new JButton("Search");
        this.add(sField);
        this.add(pField);
        this.add(button);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String word = sField.getText();
        pField.setText(searcher.search(word));
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() { //Miksei tässä ole sulkua?

            public void run() {
                Window window = new Window();
                window.buildGUI();
                window.setTitle("Britionary");
                window.pack();
                window.setVisible(true);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

    }

}
