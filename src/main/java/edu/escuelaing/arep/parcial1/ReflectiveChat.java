package edu.escuelaing.arep.parcial1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;

public class ReflectiveChat {

    private static int PORT = 30000;
    private static boolean RUNNING = true;

    public static void main(String[] args) throws Exception {
        ReflectiveChat.start();
    }

    public static void start() throws Exception {
        ServerSocket server = new ServerSocket(PORT);
        while (RUNNING) {
            Socket client = server.accept();
            System.out.println("FACADE RUNNING");
            if (client != null) {
                handleRequest(client);
            }
        }
    }

    public static void handleRequest(Socket client) throws Exception {
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String readline = in.readLine();

        String request = readline.split(" ")[1];
        URI uir = new URI(request);
        System.out.println("REQUEST " + request);

        
    }

}
 