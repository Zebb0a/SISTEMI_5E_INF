import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Sender {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int porta = 5000;

        Socket socket = new Socket(host, porta);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci un messaggio: ");
        String messaggio = scanner.nextLine();

        out.println(messaggio);
        String risposta = in.readLine();

        System.out.println("Risposta dal server: " + risposta);

        socket.close();
    }
}