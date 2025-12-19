import java.io.*;
import java.net.*;

public class Receiver {
    public static void main(String[] args) throws IOException {
        int porta = 5000;

        ServerSocket serverSocket = new ServerSocket(porta);
        System.out.println("Server avviato sulla porta " + porta);

        Socket socket = serverSocket.accept();
        System.out.println("Client connesso");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String messaggio = in.readLine();
        System.out.println("Ricevuto: " + messaggio);

        String risposta = messaggio.toUpperCase();
        out.println(risposta);

        socket.close();
        serverSocket.close();
        System.out.println("Connessione chiusa");
    }
}