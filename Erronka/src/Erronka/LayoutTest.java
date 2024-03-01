package Erronka;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LayoutTest {

	Layout layout;
	
	public void sortuLayout() {
		layout = new Layout();
	}

	@Test
	void testFormatuaEman() {
		String[] zerrenda = {"1ag3.crismull", "pvlbtnse", "Cristian", "Mulleras", "", "1ag3.crismull@tolosaldealh.eus", "", "", ""};
		
		assertEquals(layout.formatuaEman(zerrenda), "('1ag3.crismull', 'pvlbtnse', 'Cristian', 'Mulleras', NULL, '1ag3.crismull@tolosaldealh.eus', NULL, NULL, NULL)");
	}

	@Test
	void testTryParseInt() {
        assertTrue(layout.tryParseInt("123"));
        assertTrue(layout.tryParseInt("-456"));
        assertFalse(layout.tryParseInt("Cristian"));
        assertFalse(layout.tryParseInt(""));
        assertFalse(layout.tryParseInt("12.34"));
	}
	
	@Test
	void testBihurtuErabiltzailea() throws IOException {
        File selectedFile = File.createTempFile("testFile", ".csv");
        selectedFile.deleteOnExit();
		
		String csvData 	= "1ag3.andegomez;pvlbtnse;Ander;Gomez;;1ag3.andegomez@tolosaldealh.eus;;;"
						+ "\n"
						+ "1ag3.jokipeti;pvlbtnse;Jokin;Petisko;;1ag3.jokipeti@tolosaldealh.eus;;;";
		java.nio.file.Files.write(selectedFile.toPath(), csvData.getBytes());
		
        File destinationFile = File.createTempFile("testDestination", ".sql");
        destinationFile.deleteOnExit();
        
        layout.bihurtuErabiltzailea(selectedFile, destinationFile);
        
        List<String> expectedLines = Arrays.asList(
                "INSERT INTO `erabiltzailea`(`username`, `pasahitza`, `izena`, `abizenak`, `aktibo`, `email`, `helbidea`, `telefonoa`, `administratzailea`) VALUES('1ag3.andegomez', 'pvlbtnse', 'Ander', 'Gomez', NULL, '1ag3.andegomez@tolosaldealh.eus')",
                "INSERT INTO `erabiltzailea`(`username`, `pasahitza`, `izena`, `abizenak`, `aktibo`, `email`, `helbidea`, `telefonoa`, `administratzailea`) VALUES('1ag3.jokipeti', 'pvlbtnse', 'Jokin', 'Petisko', NULL, '1ag3.jokipeti@tolosaldealh.eus')"
        );
        
        List<String> actualLines = Files.readAllLines(destinationFile.toPath());
        
        assertTrue(destinationFile.exists());
        assertEquals(expectedLines, actualLines);
        
        selectedFile.delete();
        destinationFile.delete();
	}
	
	@Test
	void testBihurtuLehiaketa() throws IOException {
        File selectedFile = File.createTempFile("testFile", ".csv");
        selectedFile.deleteOnExit();
		
		String csvData 	= "BIN01;Buruz buruko txapelketa;Profesionala;2022;2022-01-03;2022-12-14"
						+ "\n"
						+ "BIN02;Buruz buruko txapelketa;Benjamin (bigarren urtea);2020;2020-01-08;2020-12-12";
		
		java.nio.file.Files.write(selectedFile.toPath(), csvData.getBytes());
		
        File destinationFile = File.createTempFile("testDestination", ".sql");
        destinationFile.deleteOnExit();
        
        layout.bihurtuLehiaketa(selectedFile, destinationFile);
        
        List<String> expectedLines = Arrays.asList(
                "INSERT INTO `lehiaketa`(`kodea`, `izena`, `kategoria`, `denboraldia`, `hasiera_data`, `bukaera_data`) VALUES('BIN01', 'Buruz buruko txapelketa', 'Profesionala', 2022, '2022-01-03', '2022-12-14')",
                "INSERT INTO `lehiaketa`(`kodea`, `izena`, `kategoria`, `denboraldia`, `hasiera_data`, `bukaera_data`) VALUES('BIN02', 'Buruz buruko txapelketa', 'Benjamin (bigarren urtea)', 2020, '2020-01-08', '2020-12-12')"
        );
        
        List<String> actualLines = Files.readAllLines(destinationFile.toPath());
        
        assertTrue(destinationFile.exists());
        assertEquals(expectedLines, actualLines);
	}

}
