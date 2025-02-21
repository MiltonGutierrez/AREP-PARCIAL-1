package edu.escuelaing.arep.parcial1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;

public class ReflectiveChat {

    private static int PORT = 30001;
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
        URI uri = new URI(request);
        System.out.println("REQUEST " + request);

        if(uri.getPath().startsWith("/compreflex")){
            handleChatRequest(out, uri);
        }
        in.close();
        out.close();
        client.close();
    }

    private static void handleChatRequest(PrintWriter out, URI resource)throws Exception{
        String fullFunction = resource.getQuery().split("=")[1];
        System.out.println("FULL FUNCTION " + fullFunction);
    }

}
 