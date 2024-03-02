package Erronka;

import javax.swing.SwingUtilities;

/**
 * .csv fitxategiak .sql-era bihurtzen dituen aplikazioa exekutatzen duen klasea.
 * @author Unax Azpirotz
 * @author Cristian Mulleras
 */
public class Erronka {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Layout();
            }
        });
	}

}
