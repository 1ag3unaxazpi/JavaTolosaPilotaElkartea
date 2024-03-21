package Erronka;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Layout klaseko metodoak testeatzeko klasea.
 * @author Unax Azpirotz
 * @author Cristian Mulleras
 */
class LayoutTest {

	/**
	 * Erabiltzaileen .csv fitxategia .sql fitxategira ondo bihurtzen duen test metodoa.
	 * @throws IOException Fitxategi errore salbuespena.
	 */
	@Test
	void testBihurtuErabiltzaileak() throws IOException {
        File selectedFile = File.createTempFile("testFile", ".csv"); // Fitxategi temporala sortzen da.
        selectedFile.deleteOnExit(); // Programa amaitzerakoan fitxategia ezabatzen da.
        
		String csvData 	= "1ag3.andegomez;pvlbtnse;Ander;Gomez;;1ag3.andegomez@tolosaldealh.eus;;;"
						+ "\n"
						+ "1ag3.jokipeti;pvlbtnse;Jokin;Petisko;;1ag3.jokipeti@tolosaldealh.eus;;;";

		java.nio.file.Files.write(selectedFile.toPath(), csvData.getBytes()); // Datuak .csv fitxategi temporalean idatzi.

		File destinationFile = File.createTempFile("testDestination", ".sql"); // Fitxategi temporala sortzen da.
		destinationFile.deleteOnExit(); // Programa amaitzerakoan fitxategia ezabatzen da.
		
		String aukera = "Erabiltzaileak";
		
		Layout.bihurtu(aukera, selectedFile, destinationFile);
		
        List<String> expectedLines = Arrays.asList(
                "INSERT INTO `erabiltzailea` (`username`, `pasahitza`, `izena`, `abizenak`, `aktibo`, `email`, `helbidea`, `telefonoa`, `administratzailea`) VALUES ('1ag3.andegomez', 'pvlbtnse', 'Ander', 'Gomez', NULL, '1ag3.andegomez@tolosaldealh.eus', NULL, NULL, NULL);",
                "INSERT INTO `erabiltzailea` (`username`, `pasahitza`, `izena`, `abizenak`, `aktibo`, `email`, `helbidea`, `telefonoa`, `administratzailea`) VALUES ('1ag3.jokipeti', 'pvlbtnse', 'Jokin', 'Petisko', NULL, '1ag3.jokipeti@tolosaldealh.eus', NULL, NULL, NULL);"
        );
        
        List<String> actualLines = Files.readAllLines(destinationFile.toPath());
        
        assertTrue(destinationFile.exists());
        assertEquals(expectedLines, actualLines);
	}
	
	/**
	 * Lehiaketen .csv fitxategia .sql fitxategira ondo bihurtzen duen test metodoa.
	 * @throws IOException Fitxategi errore salbuespena.
	 */
	@Test
	void testBihurtuLehiaketak() throws IOException {
        File selectedFile = File.createTempFile("testFile", ".csv"); // Fitxategi temporala sortzen da.
        selectedFile.deleteOnExit(); // Programa amaitzerakoan fitxategia ezabatzen da.
        
		String csvData 	= "BIN01;Buruz buruko txapelketa;Profesionala;2022;2022-01-03;2022-12-14"
						+ "\n"
						+ "BIN02;Buruz buruko txapelketa;Benjamin (bigarren urtea);2020;2020-01-08;2020-12-12";

		java.nio.file.Files.write(selectedFile.toPath(), csvData.getBytes()); // Datuak .csv fitxategi temporalean idatzi.

		File destinationFile = File.createTempFile("testDestination", ".sql"); // Fitxategi temporala sortzen da.
		destinationFile.deleteOnExit(); // Programa amaitzerakoan fitxategia ezabatzen da.
		
		String aukera = "Lehiaketak";
		
		Layout.bihurtu(aukera, selectedFile, destinationFile);
		
        List<String> expectedLines = Arrays.asList(
                "INSERT INTO `lehiaketa` (`kodea`, `izena`, `kategoria`, `denboraldia`, `hasiera_data`, `bukaera_data`) VALUES ('BIN01', 'Buruz buruko txapelketa', 'Profesionala', 2022, '2022-01-03', '2022-12-14');",
                "INSERT INTO `lehiaketa` (`kodea`, `izena`, `kategoria`, `denboraldia`, `hasiera_data`, `bukaera_data`) VALUES ('BIN02', 'Buruz buruko txapelketa', 'Benjamin (bigarren urtea)', 2020, '2020-01-08', '2020-12-12');"
        );
        
        List<String> actualLines = Files.readAllLines(destinationFile.toPath());
        
        assertTrue(destinationFile.exists());
        assertEquals(expectedLines, actualLines);
	}

}
