package Erronka;

/**
 * Lehiaketa objektuaren klasea.
 * @author Unax Azpirotz
 * @author Cristian Mulleras
 */
public class Lehiaketa {
	/** Lehiaketaren kodea. */
	public String kodea;
	/** Lehiaketaren izena. */
	public String izena;
	/** Lehiaketaren kategoria. */
	public String kategoria;
	/** Lehiaketaren denboraldia. */
	public String denboraldia;
	/** Lehiaketaren hasiera-data. */
	public String hasiera_data;
	/** Lehiaketaren bukaera-data. */
	public String bukaera_data;
	
	//OHARRA: 'denboraldia' atributua ezin du int balore mota izan, "NULL" balorea hartu ahal duelako.
	
	/**
	 * Lehiaketa objektuaren eraikitzailea (parametro bidez).
	 * @param ko Lehiaketaren kodea.
	 * @param i Lehiaketaren izena.
	 * @param ka Lehiaketaren kategoria.
	 * @param d Lehiaketaren denboraldia.
	 * @param h Lehiaketaren hasiera-data.
	 * @param b Lehiaketaren bukaera-data.
	 */
	Lehiaketa(String ko, String i, String ka, String d, String h, String b) {
		kodea = ko;
		izena = i;
		kategoria = ka;
		denboraldia = d;
		hasiera_data = h;
		bukaera_data = b;
	}
}
