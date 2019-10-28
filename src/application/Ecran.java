package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Ecran extends Parent{

	Label label;
	double valeur;

	public Ecran() {

		Group group = new Group();

		Rectangle rect1 = new Rectangle(315,100);
		rect1.setFill(Color.AQUA);
		rect1.setArcHeight(40);
		rect1.setArcWidth(40);

		Rectangle rect2 = new Rectangle(314,100);
		rect2.setFill(Color.TRANSPARENT);
		rect2.setStroke(Color.BLACK);
		rect2.setStrokeWidth(3);
		rect2.setArcHeight(40);
		rect2.setArcWidth(40);

		label = new Label();
		label.setText("0");
		label.setFont(Font.font(50));
		label.setTextFill(Color.BLACK);
		label.setPrefSize(320, 80);
		label.setMaxSize(320, 80);
		label.setPadding(new Insets(12, 15, 0, 0));
		label.setAlignment(Pos.BASELINE_RIGHT); 

		group.getChildren().addAll(rect1,rect2,label);

		this.getChildren().add(group);

	}
	
	public double getValeurEcran() {
		
		this.valeur=Double.valueOf(this.label.getText());
		return this.valeur;
		
	}

}
