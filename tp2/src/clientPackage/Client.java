package clientPackage;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import object.Operation;

public class Client {
	final static int port=9650;

	public static void main(String[] args) {
		try {
			/*le client n'est pas encore connecté*/
			System.out.println("Je suis un client pas encore connecté...");
			/*Création du client et connexion au serveur: localhost, port9644 */
			Socket socket=new Socket("localhost",port);
			System.out.println("Je suis un client connecté");
			/*flux d'entrée sortie*/
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); // pour envoyer des objets
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());			
			Scanner s=new Scanner(System.in);
			int nb1, nb2;
			char opr;
			while(true) {
				
				    System.out.print("Entrer une opération (ex: 45 * 69) ou 0 pour quitter : ");
				    String ligne = s.nextLine().trim();
				    if (ligne.equals("0")) break;

				    String[] parts = ligne.split(" ");
				    if (parts.length != 3) {
				        System.out.println("Format invalide !");
				        continue;
				    }

				    nb1 = Integer.parseInt(parts[0]);
				    opr = parts[1].charAt(0);
				    nb2 = Integer.parseInt(parts[2]);

				    Operation op = new Operation(nb1, nb2, opr);
				    oos.writeObject(op);
				    oos.flush();

				    int resultat = ois.readInt();
				    System.out.println("Résultat reçu du serveur : " + resultat);
				
			
			    
			
				}
			/*Fermeture */
			s.close();
			ois.close();
			oos.close();
			socket.close();
			System.out.println("Client terminé");
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}
