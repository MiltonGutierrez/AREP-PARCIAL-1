package edu.escuelaing.arep.parcial1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;

import static edu.escuelaing.arep.parcial1.HttpResponse.getRespoonse;

public class Facade {
    private static int PORT = 30000;
    private static boolean RUNNING = true;

    public static void main(String[] args) throws Exception {
        Facade.start();
        
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

        if (uir.getPath().startsWith("/cliente")) {
            getPage(out);
        } else if (uir.getPath().startsWith("/consulta")) {
            handleChatRequest(out, uir);
        }
        in.close();
        out.close();
        client.close();

    }

    private static void handleChatRequest(PrintWriter out, URI resource) throws Exception{
        String chatResponse = getRespoonse("compreflex?"+resource.getQuery());
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK\r\n");
        response.append("Content-Type: application/json\r\n");
        response.append("\r\n");
        response.append(chatResponse);
        out.println(response.toString());
        out.flush();
    }

    public static void getPage(PrintWriter out) {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK\r\n");
        response.append("Content-Type: text/html\r\n");
        response.append("\r\n");
        response.append("<!DOCTYPE html>\r\n" + //
                        "<html lang=\"en\">\r\n" + //
                        "    <head>\r\n" + //
                        "        <title>CHAT</title>\r\n" + //
                        "        <meta charset=\"UTF-8\">\r\n" + //
                        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                        "    </head>\r\n" + //
                        "    <body>\r\n" + //
                        "        <h1>REFLEXIVE CHAT</h1>\r\n" + //
                        "        <form action>\r\n" + //
                        "            <input type=\"text\" id=\"function\"><br><br>\r\n" + //
                        "            <input type=\"button\" value=\"Submit\" onclick=\"chat(document.getElementById('function').value)\">\r\n" + //
                        "        </form> \r\n" + //
                        "        <div id=\"getrespmsg\"></div>\r\n" + //
                        "        <script>\r\n" + //
                        "            async function chat(method) {\r\n" + //
                        "                let url = \"http://localhost:30000/consulta?comando=\";\r\n" + //
                        "                let response = await fetch (`${url}${method}`, {method: 'POST'});\r\n" + //
                        "                let obj = await response.json();\r\n" + //
                        "                console.log(obj);\r\n" + //
                        "                document.getElementById(\"getrespmsg\").innerHTML = obj;\r\n" + //
                        "            }\r\n" + //
                        "        </script>\r\n" + //
                        "    </body>\r\n" + //
                        "</html>");
        out.println(response.toString());
        out.flush();

    }
}
