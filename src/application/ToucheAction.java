package application;

import javafx.scene.paint.Color;

public class ToucheAction extends Touche {  // cette classe hérite de Touche

	// elle permet de différencier les touches Action des Numéros (par la couleur)
	// Cela évite la redondance de code 
	
	public ToucheAction(String caractere,Ecran ecran,Memoire memoire) {

		super(caractere,ecran,memoire);
		super.rectangle.setFill(Color.LIGHTGREY);

	}

}
