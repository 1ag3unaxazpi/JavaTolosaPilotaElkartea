package Erronka;

/**
 * Erabiltzailea objektuaren klasea.
 * @author Unax Azpirotz
 * @author Cristian Mulleras
 */
public class Erabiltzailea {
	/** Erabiltzailearen username. */
	public String username;
	/** Erabiltzailearen pasahitza. */
	public String pasahitza;
	/** Erabiltzailearen izena. */
	public String izena;
	/** Erabiltzailearen abizenak. */
	public String abizenak;
	/** Erabiltzailea aktibo dagoen edo ez (Aktibo: 1 / Ez aktibo: 0). */
	public String aktibo;
	/** Erabiltzailearen email-a. */
	public String email;
	/** Erabiltzailearen helbidea. */
	public String helbidea;
	/** Erabiltzailearen telefonoa. */
	public String telefonoa;
	/** Erabiltzailea administratzailea den ala ez (Da: 1 / Ez da: 0). */
	public String administratzailea;
	
	//OHARRA: 'aktibo' eta 'administratzailea' atributuak ezin dute int balore mota izan, "NULL" balorea hartu ahal dutelako.
	
	/**
	 * Erabiltzailea objektuaren eraikitzailea (parametro bidez).
	 * @param u Erabiltzailearen username.
	 * @param p Erabiltzailearen pasahitza.
	 * @param i Erabiltzailearen izena.
	 * @param ab Erabiltzailearen abizenak.
	 * @param ak Erabiltzailea aktibo dagoen edo ez (Aktibo: 1 / Ez aktibo: 0).
	 * @param e Erabiltzailearen email-a.
	 * @param h Erabiltzailearen helbidea.
	 * @param t Erabiltzailearen telefonoa.
	 * @param ad Erabiltzailea administratzailea den ala ez (Da: 1 / Ez da: 0).
	 */
	Erabiltzailea(String u, String p, String i, String ab, String ak, String e, String h, String t, String ad) {
		username = u;
		pasahitza = p;
		izena = i;
		abizenak = ab;
		aktibo = ak;
		email = e;
		helbidea = h;
		telefonoa = t;
		administratzailea = ad;
	}
}
