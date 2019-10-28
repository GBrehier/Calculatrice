package application;

public class Memoire {
	
	boolean addition;
	boolean soustraction;
	boolean multiplication;
	boolean division;
	boolean virgule;
	boolean finCalcul;
	
	double nombre1;
	double nombre2;
	double resultat;

	public Memoire() {
		this.addition=false;
		this.soustraction=false;
		this.multiplication=false;
		this.division=false;
		this.virgule=false;
		this.finCalcul=false;
		
		this.nombre1=0;
		this.nombre2=0;
		this.resultat=0;
	}

	@Override
	public String toString() {
		return ("Memoire : \n addition=" + addition + "\n soustraction=" + soustraction + "\n multiplication=" + multiplication
				+ "\n division=" + division + "\n virgule=" + virgule + "\n finCalcul=" + finCalcul + "\n\n nombre1="
				+ nombre1 + "\n nombre2=" + nombre2 + "\n resultat=" + resultat );
	}

	
	
}
