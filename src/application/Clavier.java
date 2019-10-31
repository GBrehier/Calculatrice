package application;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class Clavier extends Parent{
	
	// initialisation des attributs
	
	Ecran ecran; // pour que le clavier puisse agir sur l'écran
	Memoire memoire; // pour que le clavier agisse sur la mémoire

	GridPane gridPane; // grille contenant les touches

	// touches des numéros
	Touche touche1;
	Touche touche2;
	Touche touche3;
	Touche touche4;
	Touche touche5;
	Touche touche6;
	Touche touche7;
	Touche touche8;
	Touche touche9;
	Touche touche0;
	
	//touches des fonctions
	Touche toucheCE;
	Touche toucheC;
	Touche toucheRetour;
	Touche toucheFois;
	Touche toucheDivise;
	Touche toucheMoins;
	Touche touchePlus;
	Touche toucheEgal;
	Touche toucheVirgule;
	Touche toucheSigne;				

	public Clavier(Ecran ecran, Memoire memoire) {

		this.ecran=ecran; // on récupère l'écran et la mémoire
		this.memoire=memoire;
		
		GridPane gridPane = new GridPane(); // on crée la grille

		touche1 = new ToucheNumero("1",this.ecran,this.memoire); // création des touches numéros
		touche2 = new ToucheNumero("2",this.ecran,this.memoire);
		touche3 = new ToucheNumero("3",this.ecran,this.memoire);
		touche4 = new ToucheNumero("4",this.ecran,this.memoire);
		touche5 = new ToucheNumero("5",this.ecran,this.memoire);
		touche6 = new ToucheNumero("6",this.ecran,this.memoire);
		touche7 = new ToucheNumero("7",this.ecran,this.memoire);
		touche8 = new ToucheNumero("8",this.ecran,this.memoire);
		touche9 = new ToucheNumero("9",this.ecran,this.memoire);
		touche0 = new ToucheNumero("0",this.ecran,this.memoire);

		toucheCE = new ToucheAction("CE",this.ecran,this.memoire); // création des touches d'action
		toucheC = new ToucheAction("C",this.ecran,this.memoire);
		toucheRetour = new ToucheAction("←",this.ecran,this.memoire);
		toucheFois = new ToucheAction("x",this.ecran,this.memoire);
		toucheDivise = new ToucheAction("/",this.ecran,this.memoire);
		toucheMoins = new ToucheAction("-",this.ecran,this.memoire);
		touchePlus = new ToucheAction("+",this.ecran,this.memoire);
		toucheEgal = new ToucheAction("=",this.ecran,this.memoire);
		toucheVirgule = new ToucheAction(".",this.ecran,this.memoire);
		toucheSigne = new ToucheAction("±",this.ecran,this.memoire);		

		gridPane.add(toucheCE, 0, 0); // ajout des touches à la grille
		gridPane.add(toucheC, 1, 0);
		gridPane.add(toucheRetour, 2, 0);
		gridPane.add(toucheFois, 3, 0);
		gridPane.add(touche7, 0, 1);
		gridPane.add(touche8, 1, 1);
		gridPane.add(touche9, 2, 1);
		gridPane.add(toucheDivise, 3, 1);
		gridPane.add(touche4, 0, 2);
		gridPane.add(touche5, 1, 2);
		gridPane.add(touche6, 2, 2);
		gridPane.add(toucheMoins, 3, 2);
		gridPane.add(touche1, 0, 3);
		gridPane.add(touche2, 1, 3);
		gridPane.add(touche3, 2, 3);
		gridPane.add(touchePlus, 3, 3);
		gridPane.add(toucheSigne, 0, 4);    		
		gridPane.add(touche0, 1, 4);
		gridPane.add(toucheVirgule, 2, 4);
		gridPane.add(toucheEgal, 3, 4);

		gridPane.setHgap(5); // écart en pixel entre les éléments : 5px horizontal 3px vertical
		gridPane.setVgap(3);

		this.getChildren().add(gridPane); 

	}
	
}	
