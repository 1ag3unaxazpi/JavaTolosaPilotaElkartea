package Erronka;

import javax.swing.SwingUtilities;

public class Erronka {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Layout();
            }
        });
	}

}
