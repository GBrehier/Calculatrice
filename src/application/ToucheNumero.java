package application;

import javafx.scene.paint.Color;

public class ToucheNumero extends Touche { // cette classe hérite de Touche (comme ToucheAction)

	// elle permet de différencier les touches Numéros des Actions (par la couleur)
	// Cela évite la redondance de code 
	
	public ToucheNumero(String caractere,Ecran ecran,Memoire memoire) {
		super(caractere,ecran,memoire);
		super.rectangle.setFill(Color.LIGHTBLUE);

	}

}
