import java.io.*;
import java.net.*;
public class Talk
{
    public static void main(String[] args){
        if (args.length > 0){
            try{
                if (args[0].equals("-help")){
                    System.out.println("Youpeng Bruce Huang. \n To use this talk program, you should do this");
                    System.exit(1);
                }else if (args[0].equals("-h")){

                    System.out.println("Starting TalkClient");
                    String serverName="localhost";
                    int serverPortNumber=16466;
                    String MyMessage;

                    try{
                        Socket socket = new Socket(serverName, serverPortNumber);
                        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                        while(true)
                        {
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

                }else if (args[0].equals("-s")){

                    System.out.println("Starting TalkServer");
                    BufferedReader stdIn=null;
                    BufferedReader in=null;
                    PrintWriter out=null;
                    int serverPortNumber=16466;
                    String message=null;
                    Socket client=null;
                    ServerSocket server=null;

                    try{
                        server= new ServerSocket(serverPortNumber);
                        System.out.println("Server listening on port "+serverPortNumber);}
                    catch (IOException e)
                    {
                        System.out.println("Could not listen on port "+serverPortNumber);
                        System.exit(-1);
                    }
                    try{
                        client=server.accept();
                        System.out.println("Server accepted connection from "+client.getInetAddress());}
                    catch (IOException e)
                    {
                        System.out.println("Accept failed on port "+ serverPortNumber);
                        System.exit(-1);
                    }
                    try{
                        stdIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    }
                    catch (IOException e)
                    {
                        System.out.println("Couldn't get an inputStream from the client");
                        System.exit(-1);
                    }
                    try{
                        out = new PrintWriter(client.getOutputStream(), true);
                        in=new BufferedReader(new InputStreamReader(System.in));
                    }
                    catch (IOException e)
                    {		System.out.println("Couldn't read your input and get socket output");
                        System.exit(-1);
                    }

                    try{
                        while(true){
                            if (stdIn.ready()) {
                                System.out.println("Client:" + stdIn.readLine());}
                            if (in.ready()) {
                                message = in.readLine();
                                if (message.equals("STATUS")) {
                                    System.out.println("Port number is: " + serverPortNumber);
                                } else if (message != null) {
                                    out.println(message);
                                }
                            }
                        }
                    }
                    catch (IOException e ) {
                        System.out.println("Read failed");
                        System.exit(-1);
                    }
                }else if (args[0].equals("-a")){


                }
            } catch (Exception e){
                System.out.println("error.");
            }
        }
    }
}