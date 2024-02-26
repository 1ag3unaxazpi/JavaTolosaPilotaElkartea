package Erronka;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.*;

public class Layout {
	private static JLabel fileLabel;
    private JButton selectButton;
    private JComboBox<String> comboBox;
    private JFileChooser fileChooser = new JFileChooser();

    public Layout() {
        JFrame frame = new JFrame("Tolosa Pilota Elkartea");
        frame.setSize(600, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        try {
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
        	System.out.println(e.getMessage());
        }

        // Panel printzipala
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);

        // Panela
        JPanel topPanel = new JPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Fitxategi aukeratzailea
        fileLabel = new JLabel("Hautatutako fitxategia: ");
        topPanel.add(fileLabel);

        selectButton = new JButton("Fitxategia aukeratu");
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
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
        centerPanel.add(comboBox);

        // Botoia
        JButton actionButton = new JButton("Bihurtu");
        actionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                fitxategiaSortu(selectedOption, fileChooser.getSelectedFile());
            }
        });
        centerPanel.add(actionButton);

        frame.setVisible(true);
    }
    
    public static void fitxategiaSortu(String aukera, File selectedFile) {
    	switch (aukera) {
    	case "Erabiltzaileak":
    		String kontsulta="INSERT INTO `erabiltzailea`(`username`, `pasahitza`, `izena`, `abizenak`, `aktibo`, `email`, `helbidea`, `telefonoa`, `administratzailea`) VALUES ('";
            System.out.println("Hautatutako fitxategia: " + selectedFile.getName());
            JFileChooser helmugaChooser = new JFileChooser();
            helmugaChooser.showSaveDialog(helmugaChooser);
            File             outFile  = new File(helmugaChooser.getSelectedFile().getPath());
			try {
				FileInputStream  in      = new FileInputStream(selectedFile);
				FileOutputStream out     = new FileOutputStream(outFile);
				
				int komak=1;
				byte [] c = in.readAllBytes();
				for(int i = 0; i<c.length;i++){
					if(c[i]==';') {
						if(i==c.length-1) {
							if(c[i]==c[i-1]){
								kontsulta+=",NULL)";
							}
							else {
								kontsulta+="')";
							}
							
						}
						else if(komak%8==0) {
							if(c[i]==c[i-1]){
								kontsulta+=",NULL),('";
							}
							else if(c[i]==c[i+1]){
								kontsulta+=c[i] + "'),(";
							}else {
								kontsulta+=c[i] + "'),('";
							}
						}
						else if(c[i]==c[i+1] && c[i]==c[i-1]){
							kontsulta+=",NULL";
						}
						else if(c[i]==c[i-1]){
							System.out.println((char)c[i] + " != " + (char) c[i-1]);
							kontsulta+=",'";
						}
						else if(c[i]==c[i+1]){
							kontsulta+="',NULL";
						}
						else {
							kontsulta+="','";
						}
						komak++;
					}
					else {
						kontsulta+=(char) c[i];
					}
					
					
				}
				kontsulta+=");";
				out.write(kontsulta.getBytes());
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
            
            kontsulta+=";";
    		break;
    		
    	case "Lehiaketak":
    		
    		break;
    	}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Layout();
            }
        });
    }
}
