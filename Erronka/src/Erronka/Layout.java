package Erronka;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * .csv fitxategiak .sql-era bihurtzen dituen klasea.
 * @author Unax Azpirotz
 * @author Cristian Mulleras
 */
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

        // Panel nagusia
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
    
    /**
     * .sql fitxategia sortzen duen metodoa.
     * @param Lehioan hautatutako aukera (Erabiltzaileak/Lehiaketak).
     * @param Lehioan hautatutako .csv fitxategia.
     */
    public static void fitxategiaSortu(String aukera, File selectedFile) {
    	switch (aukera) {
	    	case "Erabiltzaileak": {
	    		
	    		JFileChooser helmugaChooser = new JFileChooser();
				helmugaChooser.showSaveDialog(helmugaChooser);
				  
				bihurtuErabiltzailea(selectedFile,  helmugaChooser.getSelectedFile());
	    		
	    		break;
	    	}
    		
	    	case "Lehiaketak": {
	  
	    		JFileChooser helmugaChooser = new JFileChooser();
				helmugaChooser.showSaveDialog(helmugaChooser);
				  
				bihurtuLehiaketa(selectedFile,  helmugaChooser.getSelectedFile());
	    		
	    		break;
	    	}
    	}
    }
    
    /**
     * .csv fitxategian dagoen informazioa .sql fitxategia onartzen duen modura bihurtzen duen metodoa.
     * @param Balore guztiak dituen zerrenda.
     * @return Lerro informazioa .sql modura.
     */
    public static String formatuaEman(String[] zer) {
    	String konts = "(";
    	for(int i = 0; i<zer.length;i++) {
    		//Zerrendako elementua int datu motara bihurtu ahal duen balorea.
    		if(tryParseInt(zer[i])) {
    			if(i==zer.length-1) {
    				konts += zer[i];
    			}
    			else {
    				konts += zer[i] + ", ";
    			}
    		}
    		//Zerrendako elementua int datu motara bihurtu ezin duen balorea.
    		else {
    			//Zerrendako azken elementua, baloreari aurrean eta atzean komatxoak jarri baina koma ez.
    			if(i==zer.length-1) {
    				konts += "'" + zer[i] + "'";
    			}
    			//Baloreari aurrean eta atzean komatxoak jarri, eta gero koma.
    			else {
    				konts += "'" + zer[i] + "', ";
    			}
    		}
    	}
    	
    	konts += ")";
    	
    	konts = konts.replaceAll("''", "NULL");
    	
    	return konts;
    }
    
    /**
     * String balorea int bihurtu ahal dadin ala ez adierazten duen metodoa.
     * @param String balorea.
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
    
    public static void bihurtuErabiltzailea(File selectedFile, File helmugaFitxategia) {
    	try {
	    	Writer             outFile  = new FileWriter(helmugaFitxategia.getPath());
	    	BufferedWriter out     = new BufferedWriter(outFile);
			BufferedReader in = new BufferedReader(new FileReader(selectedFile));
			String lerroa = in.readLine();
			
			while(lerroa!=null) {
				String kontsulta="INSERT INTO `erabiltzailea`(`username`, `pasahitza`, `izena`, `abizenak`, `aktibo`, `email`, `helbidea`, `telefonoa`, `administratzailea`) VALUES";
				
				// Elementuak zerrendan sartzen dira .csv fitxategiko datuak ';' bidez bananduz.
				String[] zerrenda = lerroa.split(";");
				
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
    
    public static void bihurtuLehiaketa(File selectedFile, File helmugaFitxategia) {
    	try {
	    	Writer             outFile  = new FileWriter(helmugaFitxategia.getPath());
	        BufferedWriter out     = new BufferedWriter(outFile);
			BufferedReader in = new BufferedReader(new FileReader(selectedFile));
			String lerroa = in.readLine();
			
			while(lerroa!=null) {
				String kontsulta="INSERT INTO `lehiaketa`(`kodea`, `izena`, `kategoria`, `denboraldia`, `hasiera_data`, `bukaera_data`) VALUES";
				
				// Elementuak zerrendan sartzen dira .csv fitxategiko datuak ';' bidez bananduz.
				String[] zerrenda = lerroa.split(";");
				
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Layout();
            }
        });
    }
}
