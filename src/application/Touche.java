package application;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class Touche extends Parent{

	String caractere;
	int hauteurTouche=64;
	int longueurTouche=75;
	Rectangle rectangle;
	Ecran ecran;
	Memoire memoire;

	public Touche(String caractere,Ecran ecran,Memoire memoire) {

		this.ecran=ecran;
		this.memoire=memoire;
		this.caractere=caractere;

		Group group = new Group();

		rectangle = new Rectangle(longueurTouche,hauteurTouche);		
		rectangle.setX(0);
		rectangle.setY(0);

		group.getChildren().add(rectangle);

		Text labelCaractere = new Text(this.caractere);
		labelCaractere.setFont(Font.font(45));
		labelCaractere.setX((rectangle.getWidth()/2)-labelCaractere.getBoundsInLocal().getWidth()/2);
		labelCaractere.setY((rectangle.getHeight()/2)+labelCaractere.getBoundsInLocal().getHeight()/4);
		group.getChildren().add(labelCaractere);

		this.getChildren().add(group);

	}
	
}
