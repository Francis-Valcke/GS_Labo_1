import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ChatClientOutputThread extends Thread {
    BufferedReader in;

    public ChatClientOutputThread(BufferedReader in){
        this.in = in;
    }


    public void run(){
        String fromServer;
        try{
            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                if (fromServer.equals("Bye."))
                    break;
            }
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        System.out.println("output thread closed");
    }


}