package Erronka;

/**
 * Datu-baseko operazio metodoak dituen klasea.
 * @author Unax Azpirotz
 * @author Cristian Mulleras
 */
public class DBOperazioak {
    
	/**
     * Erabiltzaile berri bat gehitzeko sortzen duen SQL kontsulta metodoa da.
     * @param erabiltzailea Erabiltzailea objektua (Erabiltzailea).
     * @return 'INSERT INTO' SQL kontsulta (String).
     */
    public static String queryInsertErabiltzailea(Erabiltzailea erabiltzailea) {	
		String kontsulta = "INSERT INTO `erabiltzailea` (`username`, `pasahitza`, `izena`, `abizenak`, `aktibo`, `email`, `helbidea`, `telefonoa`, `administratzailea`) " +
		"VALUES ('" + erabiltzailea.username + "', '" + erabiltzailea.pasahitza + "', '" + erabiltzailea.izena + "', '" + erabiltzailea.abizenak + "', " + erabiltzailea.aktibo + ", '" + erabiltzailea.email + "', '" + erabiltzailea.helbidea + "', '" + erabiltzailea.telefonoa + "', " + erabiltzailea.administratzailea + ");";
		
		kontsulta = kontsulta.replaceAll("'NULL'", "NULL");
		
		return kontsulta;
    }
    
    /**
     * Lehiaketa berri bat gehitzeko sortzen duen SQL kontsulta metodoa da.
     * @param lehiaketa Erabiltzailea objektua (Lehiaketa).
     * @return 'INSERT INTO' SQL kontsulta (String).
     */
    public static String queryInsertLehiaketa(Lehiaketa lehiaketa) {
		String kontsulta = "INSERT INTO `lehiaketa` (`kodea`, `izena`, `kategoria`, `denboraldia`, `hasiera_data`, `bukaera_data`) " +
		"VALUES ('" + lehiaketa.kodea + "', '" + lehiaketa.izena + "', '" + lehiaketa.kategoria + "', " + lehiaketa.denboraldia + ", '" + lehiaketa.hasiera_data + "', '" + lehiaketa.bukaera_data + "');";
		
		kontsulta = kontsulta.replaceAll("'NULL'", "NULL");
		
		return kontsulta;
    }
    
}
