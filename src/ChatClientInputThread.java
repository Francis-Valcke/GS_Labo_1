import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ChatClientInputThread extends Thread {
    BufferedReader stdIn;
    PrintWriter out;


    public ChatClientInputThread(BufferedReader stdIn, PrintWriter out){
        this.stdIn = stdIn;
        this.out = out;
    }


    public void run(){
        while(true){
            try {
                String fromUser = stdIn.readLine();
                if (fromUser != null) {
                    out.println(fromUser);
                }
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
    }


}
