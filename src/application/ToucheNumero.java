package application;

import javafx.scene.paint.Color;

public class ToucheNumero extends Touche { // cette classe h�rite de Touche (comme ToucheAction)

	// elle permet de diff�rencier les touches Num�ros des Actions (par la couleur)
	// Cela �vite la redondance de code 
	
	public ToucheNumero(String caractere,Ecran ecran,Memoire memoire) {
		super(caractere,ecran,memoire);
		super.rectangle.setFill(Color.LIGHTBLUE);

	}

}
