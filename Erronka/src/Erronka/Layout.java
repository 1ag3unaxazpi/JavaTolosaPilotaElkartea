package Erronka;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * .csv fitxategiak .sql-era bihurtzen dituen klasea.
 * @author Unax Azpirotz
 * @author Cristian Mulleras
 */
public class Layout {
	private static JFrame 				frame 					= new JFrame("Tolosa Pilota Elkartea");
	private static JPanel 				mainPanel 				= new JPanel();
	private static JPanel 				topPanel 				= new JPanel();
	private static JPanel 				centerPanel 			= new JPanel();
    private static JComboBox<String> 	comboBox 				= new JComboBox<>();
	private static JLabel 				selectLabel 			= new JLabel("Hautatutako fitxategia: ");
    private static JButton 				selectButton 			= new JButton("Fitxategia aukeratu");
    private static JFileChooser 		selectFileChooser 		= new JFileChooser();
    private static JFileChooser			destinationFileChooser	= new JFileChooser();
    private static JButton				actionButton			= new JButton("Bihurtu");

    /**
     * .csv fitxategiak .sql-era bihurtzen dituen klasearen eraikitzailea.
     */
    public Layout() {        
        // GUI-aren estiloa definitzeko.
        try {
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
        	System.out.println(e.getMessage());
        }

        // Panel nagusia.
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Goiko panela.
        topPanel.add(selectLabel);
        topPanel.add(selectButton);
        
        // Erdiko panela.
        centerPanel.add(comboBox);
        centerPanel.add(actionButton);
        
        // Desplegablea.
        comboBox.addItem("Erabiltzaileak");
        comboBox.addItem("Lehiaketak");

        // 'Fitxategia aukeratu' botoia sakatzean, .csv fitxategia aukeratzeko lehioa azalduko da.
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                int returnValue = selectFileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = selectFileChooser.getSelectedFile();
                    selectLabel.setText("Hautatutako fitxategia: " + selectedFile.getName());
                }
            }
        });

        // 'Bihurtu' botoia sakatzean .csv fitxategia .sql-ra bihurtzeko prozesua hasiko da.
        actionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                fitxategiaSortu(selectedOption, selectFileChooser.getSelectedFile());
            }
        });
        
    	// Lehioa.
    	frame.add(mainPanel);
    	frame.setSize(600, 200); //Lehioaren neurriak
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lehioa ixtean operazioa bukatzen da.
        frame.setLocationRelativeTo(null); // Lehioaren posizioa (null = erdian).
        frame.setVisible(true); //Lehioari ikusgarritasuna eman.
    }
    
    /**
     * .sql fitxategia sortzen duen metodoa.
     * @param aukera Lehioan hautatutako aukera, Erabiltzaileak/Lehiaketak (String).
     * @param selectedFile Lehioan hautatutako .csv fitxategia (File).
     */
    public static void fitxategiaSortu(String aukera, File selectedFile) {  		
		destinationFileChooser.showSaveDialog(destinationFileChooser);			  
		bihurtu(aukera, selectedFile, destinationFileChooser.getSelectedFile());
    }

    /**
     * .csv fitxategia .sql fitxategi batean bihurtzen duen metodoa.
     * @param aukera Lehioan hautatutako aukera, Erabiltzaileak/Lehiaketak (String).
     * @param selectedFile Lehioan hautatutako .csv fitxategia (File).
     * @param helmugaFitxategia Sortu den .sql fitxategia (File).
     */
    public static void bihurtu(String aukera, File selectedFile, File helmugaFitxategia) {
    	try {
	    	Writer 			outFile = new FileWriter(helmugaFitxategia.getPath());
	    	BufferedWriter 	out 	= new BufferedWriter(outFile);
			BufferedReader 	in 		= new BufferedReader(new FileReader(selectedFile));
			String 			lerroa 	= in.readLine();
			
			while(lerroa != null) {				
				// Elementuak array-an sartzen dira .csv fitxategiko datuak ';' bidez bananduz.
				String[] baloreak = lerroa.split(";", -1);
				
				// Array-ko elementu bat hutsa badado 'NULL' balorea hartuko du.
				for (int i = 0; i < baloreak.length; i++) {
					if (baloreak[i].isEmpty()) {
						baloreak[i] = "NULL";
					}
				}
				
				// SQL kontsulta.
				String kontsulta = "";
				
				switch (aukera) {
					case "Erabiltzaileak": {
						Erabiltzailea erabiltzailea = new Erabiltzailea(baloreak[0], baloreak[1], baloreak[2], baloreak[3], baloreak[4], baloreak[5], baloreak[6], baloreak[7], baloreak[8]);
						kontsulta = DBOperazioak.queryInsertErabiltzailea(erabiltzailea);
						break;
					}
					case "Lehiaketak": {
						Lehiaketa lehiaketa = new Lehiaketa(baloreak[0], baloreak[1], baloreak[2], baloreak[3], baloreak[4], baloreak[5]);
						kontsulta = DBOperazioak.queryInsertLehiaketa(lehiaketa);
						break;
					}
				}
				
				System.out.println(kontsulta);
				
				out.write(kontsulta);
				out.newLine();
				
				lerroa = in.readLine();
				
			}
			
			out.close();
			in.close();
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }

}
