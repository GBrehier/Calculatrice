package application;

import java.math.BigDecimal;
import java.math.BigInteger;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class Main extends Application {

	// initialisation des attributs
	Memoire memoire;
	Ecran ecran;
	Clavier clavier;

	@Override
	public void start(Stage primaryStage) {

		VBox root = new VBox(); // racine de l'application graphique
		Scene scene = new Scene(root,325,445); // creation de la scene

		this.ecran = new Ecran();  // creation de l'ecran, de la mémoire et du clavier
		this.memoire = new Memoire();
		this.clavier = new Clavier(ecran,memoire);

		root.getChildren().addAll(ecran,clavier); // on ajoute ces composants à la scène

		this.ecran.setTranslateX(10);
		this.clavier.setTranslateX(10);
		this.clavier.setTranslateY(5);

		evenementTouche(); // ajout des évènements des touches
		evenementClavier(root); // ajout des évènements du clavier

		primaryStage.setScene(scene);
		primaryStage.setTitle("Calculatrice");
		primaryStage.setResizable(false);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}

	private void evenementClavier(VBox root) {
		
		root.requestFocus(); // pour que l'application écoute le clavier
		root.setOnKeyPressed(new EventHandler <KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				System.out.println(event.getCode());
				switch(event.getCode()) { // selon la touche pressée
				case NUMPAD0: ajouterCaractereNumero("0");
				break;
				case NUMPAD1: ajouterCaractereNumero("1");
				break;
				case NUMPAD2: ajouterCaractereNumero("2");
				break;
				case NUMPAD3: ajouterCaractereNumero("3");
				break;
				case NUMPAD4: ajouterCaractereNumero("4");
				break;
				case NUMPAD5: ajouterCaractereNumero("5");
				break;
				case NUMPAD6: ajouterCaractereNumero("6");
				break;
				case NUMPAD7: ajouterCaractereNumero("7");
				break;
				case NUMPAD8: ajouterCaractereNumero("8");
				break;
				case NUMPAD9: ajouterCaractereNumero("9");
				break;
				case BACK_SPACE: // on suprimme le dernier numéro
					String tampon = ecran.label.getText();

					if(tampon.length()<2) { // supprimer un nombre de longueur 1 revient à mettre à 0
						ecran.label.setText("0");
					}else {
						//on passe par une sous-chaine dont on enlève le dernier caractère
						ecran.label.setText(tampon.substring(0, tampon.length()-1));
					}

					break;
				case MULTIPLY:
					memoire.nombre1=ecran.getValeurEcran(); // on récupère la valeur
					memoire.multiplication=true; // on enregistre qu'on fait une multiplication
					ecran.label.setText("0");
					break;
				case DIVIDE:
					memoire.nombre1=ecran.getValeurEcran(); // idem que multiplier mais pour diviser
					memoire.division=true;
					ecran.label.setText("0");
					break;
				case SUBTRACT:
					memoire.nombre1=ecran.getValeurEcran();// cas de la soustraction
					memoire.soustraction=true;
					ecran.label.setText("0");
					break;
				case ADD:
					memoire.nombre1=ecran.getValeurEcran();// cas de l'addition
					memoire.addition=true;
					ecran.label.setText("0");
					break;
				case ENTER:
					calcul(); // methode de calcul abordée plus bas
					break;
				case DECIMAL: // ajout d'une virgule
					if(!memoire.virgule) {  // si elle n'a pas été déjà utilisée
						
						ecran.label.setText(ecran.label.getText()+".");
						memoire.virgule=true; // on enregistre l'utilisation d'une virgule
					}else {

						System.out.println("la virgule a déjà été utilisée");

					}
					break;
					
				default : ;
				break;
				}
			}

		});
	}

	private void evenementTouche() { // rangement des évènement touche
		evenementTouchePlus();
		evenementToucheMoins();
		evenementToucheFois();
		evenementToucheDivise();
		evenementToucheEgal();
		evenementToucheC();
		evenementToucheCE();		
		evenementToucheSigne();		
		evenementToucheVirgule();		
		evenementToucheRetour();
		evenementToucheNumeroAll();
	}

	private void evenementToucheNumeroAll() { // rangement des évènements numéros
		evenementToucheNumero(this.clavier.touche0);
		evenementToucheNumero(this.clavier.touche1);
		evenementToucheNumero(this.clavier.touche2);
		evenementToucheNumero(this.clavier.touche3);
		evenementToucheNumero(this.clavier.touche4);
		evenementToucheNumero(this.clavier.touche5);
		evenementToucheNumero(this.clavier.touche6);
		evenementToucheNumero(this.clavier.touche7);
		evenementToucheNumero(this.clavier.touche8);
		evenementToucheNumero(this.clavier.touche9);
	}

	private void evenementToucheNumero(Touche touche) {

		touche.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if(!ecran.label.getText().equals("0")) { // si la valeur affichée n'est pas 0
					if(memoire.finCalcul) { // si c'est un résultat, on le remplace par le numéro
						// nouveau calcul
						ecran.label.setText(touche.caractere);
						memoire.finCalcul=false;
					}else {
						if(!(ecran.label.getText().length()>10)) { 
							// tant que le nombre n'est pas trop grand
							// on ajoute le numéro à la suite
							ecran.label.setText(ecran.label.getText()+touche.caractere);
						}else {
							System.out.println("error");
						}
					}
				}else { // si 0 est affiché, on remplace
					ecran.label.setText(touche.caractere);
				}
			}
		});

	}

	private void evenementToucheDivise() { // meme fonctionnement que pour l'evenement clavier divise
		this.clavier.toucheDivise.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				memoire.nombre1=ecran.getValeurEcran();
				memoire.division=true;
				ecran.label.setText("0");
			}
		});
	}

	private void evenementToucheFois() {// meme fonctionnement que pour l'evenement clavier multiplie
		this.clavier.toucheFois.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				memoire.nombre1=ecran.getValeurEcran();
				memoire.multiplication=true;
				ecran.label.setText("0");
			}
		});
	}

	private void evenementToucheMoins() { // meme fonctionnement que pour l'evenement clavier soustraire
		this.clavier.toucheMoins.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				memoire.nombre1=ecran.getValeurEcran();
				memoire.soustraction=true;
				ecran.label.setText("0");
			}
		});
	}

	private void evenementTouchePlus() { // meme fonctionnement que pour l'evenement clavier addition
		this.clavier.touchePlus.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				memoire.nombre1=ecran.getValeurEcran();
				memoire.addition=true;
				ecran.label.setText("0");
			}
		});
	}

	private void evenementToucheC() { // on recommence de 0 le calcul
		this.clavier.toucheC.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("1:"+memoire); // debug
				ecran.label.setText("0"); // on met à 0 la valeur
				setMemoire(new Memoire()); // on reset la mémoire
				System.out.println("2:"+memoire); //debug

			}
		});
	}

	private void evenementToucheEgal() {

		this.clavier.toucheEgal.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				calcul(); // méthode de calcul juste en dessous
			}
		});
	}
	
	private void calcul() {
		memoire.nombre2=ecran.getValeurEcran(); // on récupère le deuxième nombre

		if(memoire.addition) { // cas de l'addition
			memoire.resultat=memoire.nombre1+memoire.nombre2; 

		}else if(memoire.soustraction) { // cas de la soustraction
			memoire.resultat=memoire.nombre1-memoire.nombre2;

		}else if(memoire.multiplication) { // cas de la multiplication
			memoire.resultat=memoire.nombre1*memoire.nombre2;

		}else if(memoire.division) { // cas de la division
			memoire.resultat=memoire.nombre1/memoire.nombre2;

		}

		if((memoire.resultat%1)!=0) // test si le resultat est entier
		{
			//si c'est pas le cas, on affiche le resultat tel quel (si pas trop grand)
			if(String.valueOf(memoire.resultat).length()<12) {
				ecran.label.setText(String.valueOf(memoire.resultat));
			}else {
				ecran.label.setText("error");
			}
		}
		else // sinon on affiche le nombre en cast en entier pour enlever le .0 du nombre
		{
			if(String.valueOf(memoire.resultat).length()<12) {
				ecran.label.setText(String.valueOf((int)memoire.resultat));
			}else {
				ecran.label.setText("error");
			}

		}
		System.out.println("1:"+memoire); //debug
		setMemoire(new Memoire());
		memoire.finCalcul=true;
		System.out.println("2:"+memoire); //debug
	}

	private void evenementToucheCE() { // on remet à 0 le nombre actuel
		this.clavier.toucheCE.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ecran.label.setText("0"); 
			}
		});
	}

	private void evenementToucheSigne() { // changement de signe du nombre
		this.clavier.toucheSigne.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// on passe par des BigDecimals pour gérer le cas où 
				// changer de signe ferait passer le nombre en notation scientifique
				// on récupère la valeur de l'écran dans un BigDecimal
				BigDecimal nombreTampon= BigDecimal.valueOf(ecran.getValeurEcran()); 

				// Les BigDecimal n'acceptant que des BigDecimal, on en crée pour mul/div 
				BigDecimal multiplicateur = new BigDecimal(-1);
				BigDecimal diviseur = new BigDecimal(1);

				//On multiplie le nombre par -1
				nombreTampon=nombreTampon.multiply(multiplicateur);

				//On récupère sa valeur pour le BigInteger
				String stringTampon =String.valueOf(nombreTampon);

				//On calcule le reste du nombre
				BigDecimal reste=nombreTampon.remainder(diviseur);

				//On regarde si le reste du nombre est différent de 0 si oui pas entier
				if(reste.compareTo(BigDecimal.valueOf(0))!=0)
				{

					if(String.valueOf(nombreTampon).length()<12) {
						ecran.label.setText(String.valueOf(nombreTampon));
					}else {
						ecran.label.setText("nb trop gd");
					}

				}
				else
				{
					//On enlève le .0 du nombre
					stringTampon=stringTampon.substring(0, stringTampon.length()-2);

					//On force la conversion en entier
					BigInteger valeurint = new BigInteger(stringTampon);

					if(String.valueOf(valeurint).length()<12) {
						ecran.label.setText(String.valueOf(valeurint));
					}else {
						ecran.label.setText("nb trop gd");
					}

				}
			}
		});
	}

	private void evenementToucheVirgule() { // ajout d'une virgule, 
		
		//meme comportement qu'avec la touche clavier virgule
		
		this.clavier.toucheVirgule.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(!memoire.virgule) {  
					
					ecran.label.setText(ecran.label.getText()+clavier.toucheVirgule.caractere);
					memoire.virgule=true;
				}else {
					System.out.println("la virgule a déjà été utilisée");
				}
			}
		});
	}

	private void evenementToucheRetour() { // suppression du dernier caractere
		this.clavier.toucheRetour.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				String tampon = ecran.label.getText();

				if(tampon.length()<2) { // supprimer un nombre de longueur 1 revient à mettre à 0
					ecran.label.setText("0");
				}else {
					//on passe par une sous-chaine dont on enlève le dernier caractère
					ecran.label.setText(tampon.substring(0, tampon.length()-1));
				}

			}
		});
	}

	public void setMemoire(Memoire memoire) { 
		this.memoire = memoire;
	}
	
	private void ajouterCaractereNumero(String caractere) {
		
		if(!ecran.label.getText().equals("0")) { // si la valeur affichée n'est pas 0
			if(memoire.finCalcul) { // si c'est un résultat, on le remplace par le numéro
				// nouveau calcul
				ecran.label.setText(caractere);
				memoire.finCalcul=false;
			}else {
				if(!(ecran.label.getText().length()>10)) { 
					// tant que le nombre n'est pas trop grand
					// on ajoute le numéro à la suite
					ecran.label.setText(ecran.label.getText()+caractere);
				}else {
					System.out.println("error");
				}
			}
		}else { // si 0 est affiché, on remplace
			ecran.label.setText(caractere);
		}
	}

}
