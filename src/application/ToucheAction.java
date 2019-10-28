package application;

import javafx.scene.paint.Color;

public class ToucheAction extends Touche {

	public ToucheAction(String caractere,Ecran ecran,Memoire memoire) {

		super(caractere,ecran,memoire);
		super.rectangle.setFill(Color.LIGHTGREY);

	}

}
