package Erronka;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class Layout {
	private JLabel fileLabel;
    private JButton selectButton;
    private JComboBox<String> comboBox;

    public Layout() {
        JFrame frame = new JFrame("Tolosa Pilota Elkartea");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel printzipala
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);

        // Panela
        JPanel topPanel = new JPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Fitxategi aukeratzailea
        fileLabel = new JLabel("Hautatutako fitxategia: ");
        topPanel.add(fileLabel);

        selectButton = new JButton("Fitxategia aukeratu");
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    fileLabel.setText("Hautatutako fitxategia: " + selectedFile.getName());
                }
            }
        });
        topPanel.add(selectButton);

        // Desplegablea
        comboBox = new JComboBox<>();
        comboBox.addItem("Erabiltzaileak");
        comboBox.addItem("Lehiaketak");
        topPanel.add(comboBox);

        // Botoia
        JButton actionButton = new JButton("Bihurtu");
        actionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                JOptionPane.showMessageDialog(null, "Hautatutako aukera: " + selectedOption);
            }
        });
        topPanel.add(actionButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Layout();
            }
        });
    }
}
