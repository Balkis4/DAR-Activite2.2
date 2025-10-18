package serverPackage;
import java.net.*;
import java.io.*;

public class Server {
	/*le port ou le serveur attend*/
	final static int port=9650;

	public static void main(String[] args) {
		try {
			/*1)Création du serveur*/
			ServerSocket socketServeur =new ServerSocket(port);
			System.out.println("Je suis un serveur en attente de la connexion d'un client...");
						/*2)Attendre la connexion du client*/
			Socket socket=socketServeur.accept();
			System.out.println("Un client est connecté");
			/*Obtenir un flux en entrée et en sortie*/
			DataInputStream is=new DataInputStream(socket.getInputStream());
			DataOutputStream os=new DataOutputStream(socket.getOutputStream());
			
			while(true) {/*Définir une boucle infinie*/
				int x=is.readInt();/*Lecture du entier envoyé par le client*/
				System.out.println("Nombre recu du client"+x);
				if (x==0){/*La connexion s'arrête en tapant 0*/
					System.out.println("La connxion est terminée");
					break;
				}
				int a=x*5;
				System.out.println("Résultat de"+x+"*5="+a);
				os.writeInt(a);
				os.flush();
				System.out.println("Le résultat envoyé au client est "+a);
				
			}
			/*3)Fermeture */
			is.close();
			os.close();
			
			socketServeur.close();
			socket.close();
			System.out.println("La connexion est terminée");
		}catch(IOException e) {
			e.printStackTrace();		}
		

	}

}
