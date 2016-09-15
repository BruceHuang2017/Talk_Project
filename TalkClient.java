import java.io.*;
import java.net.*;
public class TalkClient
{
    public static void main(String[] args)	{

        if (args.length > 0){
            try{
                if (args[0].equals("Talk-help")){
                    System.out.println("sorry, no help.");
                }else if (args[0].equals("Talk-h")){
                    System.out.println("Good, Good.");
                }
            } catch (Exception e){
                System.out.println("error.");
            }
        }

        System.out.println("Starting TalkClient");
        String serverName="localhost";
        int serverPortNumber=16466;
        String MyMessage;

        try{
            // establish the socket connection between server and client
            Socket socket = new Socket(serverName, serverPortNumber);

            // read socket input stream from client(need to type in) and open a reader
            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));

            //get server responds(network)
            BufferedReader stdIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            // From the client to the server, then flush the wirter(true)
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while(true)
            {
                // still need to figure out if statement.
                if (stdIn.ready()){
                    System.out.println("Server:" + stdIn.readLine());}
                if (in.ready()) {
                    MyMessage = in.readLine();
                    if (MyMessage.equals("STATUS")){
                        System.out.println("Host name is: " + serverName + "\n" + "Port number is: " + serverPortNumber);
                    }else if(MyMessage != null){
                        out.println(MyMessage);
                    }
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Unknown host:"+serverName);
            System.exit(1);
        } catch  (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }
    }
}
