package edu.escuelaing.arep.parcial1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.Arrays;

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
        int index = fullFunction.indexOf("(");
        String function = fullFunction.substring(0, index).toLowerCase();
        String[] values = fullFunction.substring( index + 1, fullFunction.length() -1).split(",");

        System.out.println("FUNCTION " + function + " parameters " + Arrays.toString(values));
        String result = "";
        if(function.equals("class")){
            result = classFunction(values);
        }
        else if(function.equals("invoke")){
            result = invoke(values);
        }
        else if(function.equals("unaryinvoke")){
            result = unaryInvoke(values);
        }
        else if(function.equals("binaryinvoke")){
            result = binaryInvoke(values);
        }
        
        
    }

    public static String classFunction(String[] parameters) throws Exception{
        String className = parameters[0];
        Class<?> c = Class.forName(className);
        StringBuilder response = new StringBuilder();
        Field[] fields = c.getDeclaredFields();
        Method[] methods = c.getDeclaredMethods();
        response.append("{\"fields\":"+ "\""+Arrays.toString(fields) + "\"" + ",");
        response.append("{\"methods\""+"\"" +Arrays.toString(methods) + "\"" +"}");
        System.out.println(response.toString());

        return response.toString();
    }

    public static String invoke(String[] parameters){
        return "";
    }

    public static String unaryInvoke(String[] parameters){
        return "";
    }

    public static String binaryInvoke(String[] parameters){
        return "";
    }

}
 