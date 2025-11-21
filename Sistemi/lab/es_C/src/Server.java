import java.io.*;          // libreria lettura/scrittura
import java.net.*;         // libreria per il server

public class Server {
    public static void main(String[] args) {
        int porta = 1423;   // porta
        try (ServerSocket server = new ServerSocket(porta)) {
            // creazione del server sulla porta specificata
            System.out.println("Server avviato sulla porta " + porta);
            while (true) {  // ciclo while così il server rimane sempre in ascolto
                Socket client = server.accept(); // accetta la connessione di un client
                BufferedReader ricevi = new BufferedReader(new InputStreamReader(client.getInputStream())); // lettura
                PrintWriter invia = new PrintWriter(client.getOutputStream(), true); // scrittura
                String richiesta = ricevi.readLine(); // legge una riga ricevuta client
                String[] parti = richiesta.split(" "); // divide la stringa in pezzi separati da spazi
                if (parti.length == 3) {  // controlla che sia diviso in 3 parti
                    double n1 = Double.parseDouble(parti[0]); // converte il primo numero da testo a double
                    String op = parti[1];                     // operatore (+, -, *, /)
                    double n2 = Double.parseDouble(parti[2]); // converte il secondo numero
                    double risultato = 0;                     // risultato

                    // Controllo dell’operatore e calcolo
                    if (op.equals("+")) risultato = n1 + n2;
                    else if (op.equals("-")) risultato = n1 - n2;
                    else if (op.equals("*")) risultato = n1 * n2;
                    else if (op.equals("/")) {
                        if (n2 == 0) invia.println("Errore: divisione per zero"); // gestione divisione per 0
                        else risultato = n1 / n2;
                    } else {
                        invia.println("Errore: operatore non valido"); // operatore diverso
                    }
                    invia.println("Risultato: " + risultato); // invia il risultato al client
                } else {
                    invia.println("Formato non valido. Usa: numero operatore numero");
                    // se la stringa non ha 3 parti, invia messaggio di errore
                }
                client.close(); // chiude connessione col client
            }
        } catch (IOException e) {
            System.out.println("Errore nel server: " + e.getMessage()); // stampa eventuali errori
        }
    }
}
