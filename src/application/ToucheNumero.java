package application;

import javafx.scene.paint.Color;

public class ToucheNumero extends Touche {

	public ToucheNumero(String caractere,Ecran ecran,Memoire memoire) {
		super(caractere,ecran,memoire);
		super.rectangle.setFill(Color.LIGHTBLUE);

	}

}
