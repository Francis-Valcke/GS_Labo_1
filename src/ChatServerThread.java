import java.net.*;
import java.io.*;
import java.util.List;

public class ChatServerThread extends Thread {
    private Socket socket = null;
    private List<String> clients;
    private PrintWriter out;
    private BufferedReader in;
    private String clientName;

    public ChatServerThread(Socket socket, List<String> clients) {
        super("ChatServerThread");
        this.socket = socket;
        this.clients = clients;
    }

    public void run() {

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            String inputLine, outputLine;
            //Alle online mensen afprinten
            out.println("Online:");
            for(String s: clients){
                out.println(s);
            }
            out.println();

            //Naam kiezen
            out.println("Choose a name:");
            clientName = in.readLine();
            ChatServer.broadcast("Server", clientName + " has joined the chatroom");
            clients.add(clientName);

            //Zaken inlezen
            while ((inputLine = in.readLine()) != null) {
                ChatServer.broadcast(clientName,inputLine);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(String message){
        out.println(message);
    }

    public String getClientName() {
        return clientName;
    }
}