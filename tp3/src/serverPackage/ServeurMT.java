package serverPackage;

import java.io.*;
import java.net.*;

public class ServeurMT extends Thread {
	//conteur pour compter les clients
    private static int c = 0;

    // Méthode pour incrémenter le compteur
    private static int clientSuivant() {
        return ++c;
    }

    public static void main(String[] args) {
        // Créer et démarrer le thread du serveur
        new ServeurMT().start();
    }

    @Override
    public void run() {
        try (// Démarrer le serveur 
		ServerSocket socketServeur = new ServerSocket(1234)) {
            System.out.println("Serveur démarré ");

            // Boucle infinie pour accepter plusieurs clients
            while (true) {
                Socket socket = socketServeur.accept(); // Attente d’un client
                int n = clientSuivant(); // Numéro unique

                // Afficher les infos du client
                System.out.println("Client n°" + n + " connecté depuis "
                        + socket.getRemoteSocketAddress());

                // Créer un thread pour ce client
                new ClientProcess(socket, n).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class ClientProcess extends Thread {
    private Socket socket;
    private int n;

    public ClientProcess(Socket socket, int n) {
        this.socket = socket;
        this.n = n;
    }

    @Override
    public void run() {
        try {
            // Création des flux d’entrée/sortie
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Message d’accueil envoyé au client
            out.println("Bienvenue ! Vous êtes le client n°" + n);

            // Lecture des messages envoyés par le client
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client " + n + " : " + message);


                // Réponse du serveur
                out.println("Message reçu : " + message);
            }

            // Fermeture des ressources
            in.close();
            out.close();
            socket.close();
            System.out.println("Client n°" + n + " déconnecté.");

        } catch (IOException e) {
            System.out.println("Erreur avec le client n°" + n);
        }
    }
}
