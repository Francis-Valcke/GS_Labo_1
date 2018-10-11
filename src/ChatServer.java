import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    private static List<String> clientNames = new ArrayList<>();
    private static List<ChatServerThread> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        int portNumber = 1001;
        boolean listening = true;


        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                ChatServerThread newThread = new ChatServerThread(serverSocket.accept(), clientNames);
                clients.add(newThread);
                newThread.start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
    public static void broadcast(String from, String message){
        for(ChatServerThread client : clients){
            if(!client.getClientName().equals(from))
            client.send(from +": "+message);
        }
    }
}