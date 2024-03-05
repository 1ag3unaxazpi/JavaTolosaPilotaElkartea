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
     * @param aukera Lehioan hautatutako aukera (Erabiltzaileak/Lehiaketak).
     * @param selectedFile Lehioan hautatutako .csv fitxategia.
     */
    public static void fitxategiaSortu(String aukera, File selectedFile) {
    	switch (aukera) {
	    	case "Erabiltzaileak": {    		
				destinationFileChooser.showSaveDialog(destinationFileChooser);			  
				bihurtuErabiltzailea(selectedFile,  destinationFileChooser.getSelectedFile());
				
	    		break;
	    	}
    		
	    	case "Lehiaketak": {  
				destinationFileChooser.showSaveDialog(destinationFileChooser);  
				bihurtuLehiaketa(selectedFile,  destinationFileChooser.getSelectedFile());
	    		
	    		break;
	    	}
    	}
    }

    /**
     * Erabiltzaileen .csv fitxategia .sql fitxategi batean bihurtu.
     * @param selectedFile Hautatutako .csv fitxategia.
     * @param helmugaFitxategia Sortutako .sql fitxategia.
     */
    public static void bihurtuErabiltzailea(File selectedFile, File helmugaFitxategia) {
    	try {
	    	Writer 			outFile = new FileWriter(helmugaFitxategia.getPath());
	    	BufferedWriter 	out 	= new BufferedWriter(outFile);
			BufferedReader 	in 		= new BufferedReader(new FileReader(selectedFile));
			String 			lerroa 	= in.readLine();
			
			while(lerroa != null) {
				String kontsulta="INSERT INTO `erabiltzailea`(`username`, `pasahitza`, `izena`, `abizenak`, `aktibo`, `email`, `helbidea`, `telefonoa`, `administratzailea`) VALUES";
				
				// Elementuak zerrendan sartzen dira .csv fitxategiko datuak ';' bidez bananduz.
				String[] zerrenda = lerroa.split(";", -1);
				
				// .sql formatua emanda kontsultan gehitzen da.
				kontsulta += formatuaEman(zerrenda);
				
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
    
    /**
     * Lehiaketen .csv fitxategia .sql fitxategi batean bihurtu.
     * @param selectedFile Hautatutako .csv fitxategia.
     * @param helmugaFitxategia Sortutako .sql fitxategia.
     */
    public static void bihurtuLehiaketa(File selectedFile, File helmugaFitxategia) {
    	try {
	    	Writer 			outFile = new FileWriter(helmugaFitxategia.getPath());
	        BufferedWriter 	out 	= new BufferedWriter(outFile);
			BufferedReader 	in 		= new BufferedReader(new FileReader(selectedFile));
			String 			lerroa 	= in.readLine();
			
			while(lerroa != null) {
				String kontsulta="INSERT INTO `lehiaketa`(`kodea`, `izena`, `kategoria`, `denboraldia`, `hasiera_data`, `bukaera_data`) VALUES";
				
				// Elementuak zerrendan sartzen dira .csv fitxategiko datuak ';' bidez bananduz.
				String[] zerrenda = lerroa.split(";", -1);
				
				// .sql formatua emanda kontsultan gehitzen da.
				kontsulta += formatuaEman(zerrenda);
				
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
    
    /**
     * .csv fitxategian dagoen informazioa .sql fitxategia onartzen duen formatura bihurtzen duen metodoa.
     * @param zer Elementuak dituen zerrenda.
     * @return Datu lerroa .sql formatuan.
     */
    public static String formatuaEman(String[] zer) {
    	String konts = "(";
    	for(int i = 0; i < zer.length;i++) {
    		//Zerrendako elementua int datu motara bihurtu ahal duen balorea.
    		if(tryParseInt(zer[i])) {
    			//Zerrendako azken elementua, 'konts' aldagaian gehituko da.
    			if(i == zer.length-1) {
    				konts += zer[i];
    			}
    			//'konts' aldagaian zerrendako elementua eta koma gehituko da.
    			else {
    				konts += zer[i] + ", ";
    			}
    		}
    		//Zerrendako elementua int datu motara bihurtu ezin duen balorea.
    		else {
    			//Zerrendako azken elementua, 'konts' aldagaian gehituko da (bi komatxoen artean).
    			if(i == zer.length-1) {
    				konts += "'" + zer[i] + "'";
    			}
    			//'konts' aldagaian zerrendako elementua (bi komatxoen artean) eta koma gehituko da.
    			else {
    				konts += "'" + zer[i] + "', ";
    			}
    		}
    	}
    	
    	//.sql datu lerroaren amaiera.
    	konts += ");";
    	
    	//Hutsak diren baloreak 'NULL' bihurtu.
    	konts = konts.replaceAll("''", "NULL");
    	
    	return konts;
    }
    
    /**
     * String balorea int bihurtu ahal dadin ala ez adierazten duen metodoa.
     * @param str Zerrendako elementua (String formatuarekin).
     * @return Egia edo gezurra.
     */
    public static boolean tryParseInt(String str) {  	
    	try {
    		Integer.parseInt(str);
    		return true;
    	}
    	catch(NumberFormatException e) {
    		return false;
    	}  	
    }

}
