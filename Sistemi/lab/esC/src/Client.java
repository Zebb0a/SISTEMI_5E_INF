import java.io.*;          // Libreria per input/output (lettura e scrittura dati)
import java.net.*;         // Libreria per la rete (socket, connessioni)

public class Client {
    public static void main(String[] args) {
        String indirizzo = "localhost"; // Indirizzo del server (localhost = stessa macchina)
        int porta = 1423;               // porta
        try (Socket socket = new Socket(indirizzo, porta); // Crea un socket e si collega al server
             BufferedReader ricevi = new BufferedReader(new InputStreamReader(socket.getInputStream())); // lettura dati dal server

             PrintWriter invia = new PrintWriter(socket.getOutputStream(), true); // Sscrittura

             BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in))) { // lettura dal utente

            System.out.println("Connesso al server!");
            System.out.println("Scrivi un calcolo:");

            String calcolo = tastiera.readLine(); // legge il calcolo
            invia.println(calcolo);               // invia il calcolo al server

            String risposta = ricevi.readLine();  // riceve la risposta dal server
            System.out.println("Risposta del server: " + risposta);

        } catch (IOException e) {
            System.out.println("Errore nel client: " + e.getMessage()); // stampa eventuali errori di connessione
        }
    }
}
