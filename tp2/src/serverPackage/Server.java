package serverPackage;
import java.net.*;
import java.io.*;
import object.Operation;

public class Server {
	/*le port ou le serveur attend*/
	final static int port=9650;

	public static void main(String[] args) {
		try /*1)Création du serveur*/
		 {	ServerSocket socketServeur = new ServerSocket(port);
			System.out.println("Je suis un serveur en attente de la connexion d'un client...");
			/*2)Attendre la connexion du client*/
			Socket socket=socketServeur.accept();
			System.out.println("Un client est connecté");
			/*Obtenir un flux en entrée et en sortie*/
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());			
			while(true) {/*Définir une boucle infinie*/
				try {	
					Operation op = (Operation) ois.readObject();
					
					int nb1 = op.getNb1();
					int nb2 = op.getNb2();
					char opr = op.getOpr();
                
					int resultat = 0;
					switch(opr) {
                    	case '+': resultat = nb1 + nb2; break;
                    	case '-': resultat = nb1 - nb2; break;
                    	case '*': resultat = nb1 * nb2; break;
                    	case '/':
                    		if(nb2 != 0)
                    			resultat = nb1 / nb2;
                    		else {
                    			System.out.println("Erreur : division par zéro !");
                    		}
                    		break;
                    	default:
                    		System.out.println("Erreur : opérateur invalide !");
                    		
					}
				/*pour envoyer le resultat au client*/
					oos.writeInt(resultat);
					oos.flush();

					System.out.println("Calcul reçu : " + nb1 + " " + opr + " " + nb2 + " = " + resultat);
					System.out.println("Résultat envoyé au client");
                
                
				
				}
				 catch (EOFException e) {
	                    // Le client s'est déconnecté proprement
	                    System.out.println("Le client s'est déconnecté.");
	                    break;
	                } 
				catch(ClassNotFoundException e) {
                    System.out.println("Erreur : classe Operation introuvable !");
                    }
                
				
			}
			/*Fermeture*/
			ois.close();
            oos.close();
            socket.close();
            socketServeur.close();
            System.out.println("Connexion terminée.");

		}catch(IOException e) {
			e.printStackTrace();		}}}
		
	
		
		
	
	


