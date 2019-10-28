package application;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Format;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
//import net.objecthunter.exp4j.Expression;
//import net.objecthunter.exp4j.ExpressionBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Main extends Application {

	double x;
	String y;
	Memoire memoire;
	Ecran ecran;
	Clavier clavier;

	@Override
	public void start(Stage primaryStage) {

		VBox root = new VBox();
		Scene scene = new Scene(root,325,445);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		this.ecran = new Ecran();
		this.memoire = new Memoire();
		this.clavier = new Clavier(ecran,memoire);

		root.getChildren().addAll(ecran,clavier);

		this.ecran.setTranslateX(10);
		this.clavier.setTranslateX(10);
		this.clavier.setTranslateY(5);

		evenementTouche();
		root.requestFocus();
		root.setOnKeyPressed(new EventHandler <KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				System.out.println(event.getCode());
				switch(event.getCode()) {
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
				case BACK_SPACE:
					break;
				case MULTIPLY:
					break;
				case DIVIDE:
					break;
				case SUBTRACT:
					break;
				case ADD:
					break;
				case ENTER:
					break;
				case DECIMAL:
					if(!memoire.virgule) {  // ne marche qu'avec la version basique, possibilité 
						// plusieurs virgules dans la version complexe
						ecran.label.setText(ecran.label.getText()+".");
						memoire.virgule=true;
					}else {

						System.out.println("la virgule a déjà été utilisée");

					}
					break;
					
				default : ;
				break;
				}
			}

		});
	//	this.ecran.label.setOnKeyPressed();

		primaryStage.setScene(scene);
		primaryStage.setTitle("Calculatrice");
		primaryStage.setResizable(false);
		primaryStage.show();

		//	Expression calc = new ExpressionBuilder("3+4*8-60/3").build();
		//		double result1 = calc.evaluate();
		//	System.out.println(result1);

		/* METHOD TO CHECK IF THE NUMBER IS AN INTEGER
		if((number%1)!=0)
		{
			System.out.println("not a integer");
		}
		else
		{
			System.out.println("integer");
		}		

		double test =654878.156214781;
		System.out.println(formatNombreVirgule(test));
		 */

		

	}

	public static void main(String[] args) {
		launch(args);

	}

	private void evenementTouche() {
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

	private void evenementToucheNumeroAll() {
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

				if(!ecran.label.getText().equals("0")) {
					if(memoire.finCalcul) {
						ecran.label.setText(touche.caractere);
						memoire.finCalcul=false;
					}else {
						if(!(ecran.label.getText().length()>10)) {
							ecran.label.setText(ecran.label.getText()+touche.caractere);
						}else {
							System.out.println("nombre trop grand");
						}
					}
				}else {
					ecran.label.setText(touche.caractere);
				}

			}

		});

	}

	private void evenementToucheDivise() {
		this.clavier.toucheDivise.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				memoire.nombre1=ecran.getValeurEcran();
				memoire.division=true;
				ecran.label.setText("0");

			}
		});
	}

	private void evenementToucheFois() {
		this.clavier.toucheFois.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				memoire.nombre1=ecran.getValeurEcran();
				memoire.multiplication=true;
				ecran.label.setText("0");

			}
		});
	}

	private void evenementToucheMoins() {
		this.clavier.toucheMoins.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				memoire.nombre1=ecran.getValeurEcran();
				memoire.soustraction=true;
				ecran.label.setText("0");

			}
		});
	}

	private void evenementTouchePlus() {
		this.clavier.touchePlus.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				memoire.nombre1=ecran.getValeurEcran();
				memoire.addition=true;
				ecran.label.setText("0");

			}
		});
	}

	private void evenementToucheC() {
		this.clavier.toucheC.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("1:"+memoire);
				ecran.label.setText("0");
				setMemoire(new Memoire());
				System.out.println("2:"+memoire);

			}



		});
	}

	private void evenementToucheEgal() {

		this.clavier.toucheEgal.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				memoire.nombre2=ecran.getValeurEcran();


				if(memoire.addition) {
					memoire.resultat=memoire.nombre1+memoire.nombre2;

				}else if(memoire.soustraction) {
					memoire.resultat=memoire.nombre1-memoire.nombre2;

				}else if(memoire.multiplication) {
					memoire.resultat=memoire.nombre1*memoire.nombre2;

				}else if(memoire.division) {
					memoire.resultat=memoire.nombre1/memoire.nombre2;

				}

				if((memoire.resultat%1)!=0)
				{
					if(String.valueOf(memoire.resultat).length()<12) {
						ecran.label.setText(String.valueOf(memoire.resultat));
					}else {
						ecran.label.setText("nb trop gd");
					}

					// PARTIE POUR AJOUTER LE CHECK DES NOMBRES A VIRGULE


				}
				else
				{
					if(String.valueOf(memoire.resultat).length()<12) {
						ecran.label.setText(String.valueOf((int)memoire.resultat));
					}else {
						ecran.label.setText("nb trop gd");
					}

				}
				System.out.println("1:"+memoire);
				setMemoire(new Memoire());
				memoire.finCalcul=true;
				System.out.println("2:"+memoire);
			}



		});
	}

	private void evenementToucheCE() {
		this.clavier.toucheCE.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ecran.label.setText("0");

			}
		});
	}

	private void evenementToucheSigne() {
		this.clavier.toucheSigne.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

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

	private void evenementToucheVirgule() {
		this.clavier.toucheVirgule.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(!memoire.virgule) {  // ne marche qu'avec la version basique, possibilité 
					// plusieurs virgules dans la version complexe
					ecran.label.setText(ecran.label.getText()+clavier.toucheVirgule.caractere);
					memoire.virgule=true;
				}else {

					System.out.println("la virgule a déjà été utilisée");

				}
			}
		});
	}

	private void evenementToucheRetour() {
		this.clavier.toucheRetour.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				String tampon = ecran.label.getText();

				if(tampon.length()<2) {
					ecran.label.setText("0");
				}else {
					ecran.label.setText(tampon.substring(0, tampon.length()-1));
				}

			}
		});
	}

	public void setMemoire(Memoire memoire) {
		this.memoire = memoire;
	}

	private String formatNombreVirgule(double valeur) {

		int partieEntiere = (int) valeur;
		String stringPartieEntiere = String.valueOf(partieEntiere);
		System.out.println("Partie entière: "+stringPartieEntiere);

		double partieDecimale = valeur%1;
		String stringPartieDecimale = String.valueOf(partieDecimale);
		System.out.println("Partie décimale: "+stringPartieDecimale);

		String stringPartieDecimaleFinale = stringPartieDecimale.substring(2,(11-(stringPartieEntiere.length()+1)+2));

		String nombreFinal = stringPartieEntiere+"."+stringPartieDecimaleFinale;

		return nombreFinal;
	}

	private String formatGrandDecimal(double valeur) {

		BigDecimal test = BigDecimal.valueOf(111111111);

		System.out.println(test);
		//		String nb = format.fromBigDecimal(test);
		return "0";
	}
	
	private void ajouterCaractereNumero(String caractere) {
		if(!ecran.label.getText().equals("0")) {
			if(memoire.finCalcul) {
				ecran.label.setText(caractere);
				memoire.finCalcul=false;
			}else {
				if(!(ecran.label.getText().length()>10)) {
					ecran.label.setText(ecran.label.getText()+caractere);
				}else {
					System.out.println("nombre trop grand");
				}
			}
		}else {
			ecran.label.setText(caractere);
		}
	}

}
