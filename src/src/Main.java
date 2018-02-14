//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 2: Noemi Lemonnier 40001085
//Description: Main driver class to launch the authentification window
//--------------------------------------------------------
package src;
import javax.swing.*;

public class Main{



	private static AuthentificationLayout authentificationLayout = new AuthentificationLayout();

	public static void main(String[] args) {
		// Use the system theme, this is purely aesthetic
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		//displaying the window
		authentificationLayout.frame.setVisible(true);


	}


}

