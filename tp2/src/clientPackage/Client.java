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
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			Scanner s=new Scanner(System.in);
			String op;			
			while (true) {
                System.out.println("\nEntrez une opération (ex: 55 * 25) ou 'exit' pour quitter :");
                op = s.nextLine().trim();

                // Vérification avant envoi
                if (op.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    System.out.println("Déconnexion...");
                    break;
                }

                // Validation simple de la chaîne
                if (!op.matches("\\d+(\\.\\d+)?\\s*[+\\-*/]\\s*\\d+(\\.\\d+)?")) {
                    System.out.println("Format invalide");
                    continue;
                }
                out.println(op);
                String reponse = in.readLine();
                if (reponse != null)
                    System.out.println(" Réponse du serveur : " + reponse);
            }
			
			/*Fermeture */
			s.close();
			in.close();
			out.close();
			socket.close();
			System.out.println("Client terminé");
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}
