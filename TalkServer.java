import java.io.*;
import java.net.*;
public class TalkServer
{
	public static void main(String [] args)
	{
		System.out.println("Starting TalkServer");
		BufferedReader stdIn=null;
		BufferedReader in=null;
		PrintWriter out=null;
		int serverPortNumber=16466;
		String message=null;
		Socket client=null;
		ServerSocket server=null;

	//open a server socket
	try{
		server= new ServerSocket(serverPortNumber);
		System.out.println("Server listening on port "+serverPortNumber);}
	catch (IOException e)
	{
		System.out.println("Could not listen on port "+serverPortNumber);
		System.exit(-1);
	}
	// listen for and accept connections from clients
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
			message=in.readLine();
            out.println(message);
		}
	}
	catch (IOException e) {
		System.out.println("Read failed");
		System.exit(-1);
	}
}
}
