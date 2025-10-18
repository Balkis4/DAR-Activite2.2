package object;

import java.io.*;
public class Operation implements Serializable {
	private int nb1; /*pour le 1er nombre*/
	private int nb2; /*pour le 2eme nombre*/
	private char opr; /*pour les operateurs /,*,-,+,*/
	
	/*constructeur pour initialiser l'objet*/
	public Operation(int nb1,int nb2,char opr) {
		this.nb1=nb1;
		this.nb2=nb2;
		this.opr=opr;
	}
	
	public int getNb1() {
		return nb1;
	}
	public int getNb2() {
		return nb2;
	}
	public char getOpr() {
		return opr;
	}

}
