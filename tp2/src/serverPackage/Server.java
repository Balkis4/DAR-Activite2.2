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
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
            String op;
			while(true) {/*Définir une boucle infinie*/
				op = in.readLine();/*Lecture de l'operation envoyé par le client*/
				System.out.println("operation recu du client"+op);
				if (op== null || op.equalsIgnoreCase("exit")){/*fin de connexion si la chaine vide*/
					System.out.println("La connxion est terminée");
					break;
				}
				System.out.println("Opération reçue du client : " + op);
				
				String[] elements = op.split(" ");
				if (elements.length != 3) {
                    out.println("Erreur : format invalide ");
                    continue;
                }
				try {
                    double op1 = Double.parseDouble(elements[0]);
                    String operateur = elements[1];
                    double op2 = Double.parseDouble(elements[2]);
                    double resultat = 0;

                    switch (operateur) {
                        case "+": resultat = op1 + op2; break;
                        case "-": resultat = op1 - op2; break;
                        case "*": resultat = op1 * op2; break;
                        case "/":
                            if (op2 == 0) {
                                out.println("Erreur : division par zéro !");
                                continue;
                            }
                            resultat = op1 / op2;
                            break;
                        default:
                            out.println("Erreur : opérateur invalide !");
                            continue;
                    }

                    out.println("Résultat = " + resultat);
                    System.out.println("Résultat envoyé : " + resultat);

                } catch (NumberFormatException e) {
                	 out.println("Erreur : opérandes non numériques !");
                }
                }
				
				
				
		
			/*3)Fermeture */
			in.close();
			out.close();
			
			socketServeur.close();
			socket.close();
			System.out.println("La connexion est terminée");
		}catch(IOException e) {
			e.printStackTrace();		}
		}
	}
		

	


