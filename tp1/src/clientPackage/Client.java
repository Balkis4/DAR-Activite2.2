package clientPackage;
import java.net.*;
import java.io.*;
import java.util.Scanner;

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
			DataOutputStream os =new DataOutputStream(socket.getOutputStream());
			DataInputStream is=new DataInputStream(socket.getInputStream());
			
			Scanner s=new Scanner(System.in);
			int x;
			do {
				System.out.println("Entrer un entier");
				x=s.nextInt();
			
				os.writeInt(x);
				os.flush();
				if(x!=0) {
					int a=is.readInt();
					System.out.println("Le résultat envoyé par le serveur"+a);
				}
			}while(x!=0);
			
			/*Fermeture */
			s.close();
			is.close();
			os.close();
			socket.close();
			System.out.println("Client terminé");
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}
