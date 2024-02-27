package Erronka;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

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
    		
    		try {
    			JFileChooser helmugaChooser = new JFileChooser();
	            helmugaChooser.showSaveDialog(helmugaChooser);
	            Writer             outFile  = new FileWriter(helmugaChooser.getSelectedFile().getPath());
	            BufferedWriter out     = new BufferedWriter(outFile);
				BufferedReader in = new BufferedReader(new FileReader(selectedFile));
				String lerroa = in.readLine();
				
				while(lerroa!=null) {
					String kontsulta="INSERT INTO `erabiltzailea`(`username`, `pasahitza`, `izena`, `abizenak`, `aktibo`, `email`, `helbidea`, `telefonoa`, `administratzailea`) VALUES";
					
					String[] zerrenda = lerroa.split(";");
					
					kontsulta += formatuaEman(zerrenda);
					
					System.out.println(kontsulta);
					
					out.write(kontsulta);
					out.newLine();
					
					lerroa = in.readLine();
					
				}
				
				out.close();
				in.close();
				
				
	            
	            
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    		break;
    		
    	case "Lehiaketak":
  
    		try {
    			JFileChooser helmugaChooser = new JFileChooser();
	            helmugaChooser.showSaveDialog(helmugaChooser);
	            Writer             outFile  = new FileWriter(helmugaChooser.getSelectedFile().getPath());
	            BufferedWriter out     = new BufferedWriter(outFile);
				BufferedReader in = new BufferedReader(new FileReader(selectedFile));
				String lerroa = in.readLine();
				
				while(lerroa!=null) {
					String kontsulta="INSERT INTO `lehiaketa`(`kodea`, `izena`, `kategoria`, `denboraldia`, `hasiera_data`, `bukaera_data`) VALUES";
					
					String[] zerrenda = lerroa.split(";");
					
					kontsulta += formatuaEman(zerrenda);
					
					System.out.println(kontsulta);
					
					out.write(kontsulta);
					out.newLine();
					
					lerroa = in.readLine();
					
				}
				
				out.close();
				in.close();
				
				
	            
	            
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    		break;
    	}
    }
    
    public static String formatuaEman(String[] zer) {
    	String konts = "(";
    	for(int i = 0; i<zer.length;i++) {
    		if(tryParseInt(zer[i])) {
    			if(i==zer.length-1) {
    				konts += zer[i];
    			}
    			else {
    				konts += zer[i] + ", ";
    			}
    		}
    		else {
    			if(i==zer.length-1) {
    				konts += "'" + zer[i] + "'";
    			}
    			else {
    				konts += "'" + zer[i] + "', ";
    			}
    		}
    	}
    	
    	konts += ")";
    	
    	konts = konts.replaceAll("''", "NULL");
    	
    	return konts;
    }
    
    public static boolean tryParseInt(String str) {
    	
    	try {
    		Integer.parseInt(str);
    		return true;
    	}
    	catch(NumberFormatException e) {
    		return false;
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
