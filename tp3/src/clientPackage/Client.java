package clientPackage;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connecté au serveur sur " + socket.getRemoteSocketAddress());

            // Lecture du message d'accueil
            System.out.println(in.readLine());

            // Boucle d'échange de messages
            String message;
            while (true) {
                System.out.print("Vous : ");
                message = sc.nextLine();
                out.println(message); // Envoi au serveur


                // Réponse du serveur
                String reponse = in.readLine();
                System.out.println("Serveur : " + reponse);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sc.close(); // Fermeture du scanner
        }
    }
}
