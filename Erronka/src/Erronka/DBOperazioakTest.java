package Erronka;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * DBOperazioak klaseko metodoak testeatzeko klasea.
 * @author Unax Azpirotz
 * @author Cristian Mulleras
 */
class DBOperazioakTest {
	
	/**
	 * Erabiltzailea gehitzeko SQL kontsulta ondo sortzen duen test metodoa.
	 */
	@Test
	void testQueryInsertErabiltzailea() {
		Erabiltzailea erabiltzailea = new Erabiltzailea("1ag3.andegomez", "pvlbtnse", "Ander", "Gomez", "NULL", "1ag3.andegomez@tolosaldealh.eus", "NULL", "NULL", "NULL");
		
		String expectedQuery = "INSERT INTO `erabiltzailea` (`username`, `pasahitza`, `izena`, `abizenak`, `aktibo`, `email`, `helbidea`, `telefonoa`, `administratzailea`) " +
		"VALUES ('1ag3.andegomez', 'pvlbtnse', 'Ander', 'Gomez', NULL, '1ag3.andegomez@tolosaldealh.eus', NULL, NULL, NULL);";
		
		assertEquals(DBOperazioak.queryInsertErabiltzailea(erabiltzailea), expectedQuery);
	}
	
	/**
	 * Lehiaketa gehitzeko SQL kontsulta ondo sortzen duen test metodoa.
	 */
	@Test
	void testQueryInsertLehiaketa() {
		Lehiaketa lehiaketa = new Lehiaketa("BIN01", "Buruz buruko txapelketa", "Profesionala", "2022", "2022-01-03", "2022-12-14");
		
		String expectedQuery = "INSERT INTO `lehiaketa` (`kodea`, `izena`, `kategoria`, `denboraldia`, `hasiera_data`, `bukaera_data`) " +
		"VALUES ('BIN01', 'Buruz buruko txapelketa', 'Profesionala', 2022, '2022-01-03', '2022-12-14');";
		
		assertEquals(DBOperazioak.queryInsertLehiaketa(lehiaketa), expectedQuery);
	}
	
}
