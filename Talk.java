import java.io.*;
import java.net.*;
public class Talk {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                if (args[0].equals("-help")) {
                    System.out.println(
                            "Youpeng Bruce Huang. \n" +
                            "To use this talk program, command-line is required. Three are four types of commond-lines: \n" +
                                    "\"_-h\", \"_-s\", \"_-a\" and \"_-help\"."
                    );
                    System.exit(1);
                } else if (args[0].equals("-h")) {
                    String serverName = "localhost";
                    int serverPortNumber = 16466;

                    /* if statement for args analysis. Totally four options. */
                    if (args.length == 2) {
                        serverName = args[1];
                    } else if (args.length == 3) {
                        if (args[1].equals("-p")) {
                            try {
                                serverPortNumber = Integer.parseInt(args[2]);
                            } catch (Exception e) {
                                System.out.println("Port number input error, has to be integer.");
                                System.exit(-1);
                            }
                        } else {
                            System.out.println("Please types in \'_-p_(portnumber)\' .");
                            System.exit(-1);
                        }
                    } else if (args.length == 4) {
                        if (args[2].equals("-p")) {
                            serverName = args[1];
                            try {
                                serverPortNumber = Integer.parseInt(args[3]);
                            } catch (Exception e) {
                                System.out.println("Port number input error, has to be integer.");
                                System.exit(-1);
                            }
                        } else {
                            System.out.println("Please types in \'(hostname|IPaddress)_-p_(portnumber)\' .");
                            System.exit(-1);
                        }
                    }

                    /* client start. */
                    System.out.println("Starting Talk Client with port number: \"" + serverPortNumber
                            + "\" and server name: \"" + serverName + "\"");
                    String MyMessage;
                    try {
                        Socket socket = new Socket(serverName, serverPortNumber);
                        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        while (true) {
                            if (stdIn.ready()) {
                                System.out.println("Server:" + stdIn.readLine());
                            }
                            if (in.ready()) {
                                MyMessage = in.readLine();
                                if (MyMessage.equals("STATUS")) {
                                    System.out.println("Host name is: " + serverName + "\n" + "Port number is: " + serverPortNumber);
                                } else {
                                    out.println(MyMessage);
                                }
                            }
                        }
                    } catch (UnknownHostException e) {
                        System.out.println("Unknown host: " + serverName);
                        System.exit(1);
                    } catch (IOException e) {
                        System.out.println("No I/O");
                        System.exit(1);
                    }
                } else if (args[0].equals("-s")) {
                    int serverPortNumber = 16466;

                    /* if statement check and friendly remind input type. */
                    if (args.length == 3) {
                        if (args[1].equals("-p")) {
                            try {
                                serverPortNumber = Integer.parseInt(args[2]);
                            } catch (Exception e) {
                                System.out.println("Port number input error, has to be integer.");
                                System.exit(-1);
                            }
                        } else {
                            System.out.println("Please type in \'_-p_(portnumber)\' .");
                            System.exit(-1);
                        }
                    }

                    /* server start. */
                    System.out.println("Starting Talk Server with port number \"" + serverPortNumber + "\"");
                    BufferedReader stdIn = null;
                    BufferedReader in = null;
                    PrintWriter out = null;
                    String message;
                    Socket client = null;
                    ServerSocket server = null;
                    try {
                        server = new ServerSocket(serverPortNumber);
                        System.out.println("Server listening on port " + serverPortNumber);
                    } catch (IOException e) {
                        System.out.println("Could not listen on port " + serverPortNumber);
                        System.exit(-1);
                    }
                    try {
                        client = server.accept();
                        System.out.println("Server accepted connection from " + client.getInetAddress());
                    } catch (IOException e) {
                        System.out.println("Accept failed on port " + serverPortNumber);
                        System.exit(-1);
                    }
                    try {
                        stdIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    } catch (IOException e) {
                        System.out.println("Couldn't get an inputStream from the client");
                        System.exit(-1);
                    }
                    try {
                        out = new PrintWriter(client.getOutputStream(), true);
                        in = new BufferedReader(new InputStreamReader(System.in));
                    } catch (IOException e) {
                        System.out.println("Read and Write error.");
                        System.exit(-1);
                    }
                    try {
                        while (true) {
                            if (stdIn.ready()) {
                                System.out.println("Client:" + stdIn.readLine());
                            }
                            if (in.ready()) {
                                message = in.readLine();
                                if (message.equals("STATUS")) {
                                    System.out.println("Port number is: " + serverPortNumber);
                                } else {
                                    out.println(message);
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Read failed");
                        System.exit(-1);
                    }
                } else if (args[0].equals("-a")) {
                    String serverName = "localhost";
                    int serverPortNumber = 16466;

                    /* if statement for args analysis. Totally four options. */
                    if (args.length == 2) {
                        serverName = args[1];
                    } else if (args.length == 3) {
                        if (args[1].equals("-p")) {
                            try {
                                serverPortNumber = Integer.parseInt(args[2]);
                            } catch (Exception e) {
                                System.out.println("Port number input error, has to be integer.");
                                System.exit(-1);
                            }
                        } else {
                            System.out.println("Please types in \'_-p_(portnumber)\' .");
                            System.exit(-1);
                        }
                    } else if (args.length == 4) {
                        if (args[2].equals("-p")) {
                            serverName = args[1];
                            try {
                                serverPortNumber = Integer.parseInt(args[3]);
                            } catch (Exception e) {
                                System.out.println("Port number input error, has to be integer.");
                                System.exit(-1);
                            }
                        } else {
                            System.out.println("Please types in \'(hostname|IPaddress)_-p_(portnumber)\' .");
                            System.exit(-1);
                        }
                    }

                    /* try client. */
                    System.out.println("Starting Talk Client with port number: \"" + serverPortNumber
                            + "\" and server name: \"" + serverName + "\"");
                    String MyMessage;
                    try {
                        Socket socket = new Socket(serverName, serverPortNumber);
                        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        while (true) {
                            if (stdIn.ready()) {
                                System.out.println("Server:" + stdIn.readLine());
                            }
                            if (in.ready()) {
                                MyMessage = in.readLine();
                                if (MyMessage.equals("STATUS")) {
                                    System.out.println("Host name is: " + serverName + "\n" + "Port number is: " + serverPortNumber);
                                } else {
                                    out.println(MyMessage);
                                }
                            }
                        }
                    } catch (UnknownHostException e) {
                        System.out.println("Unknown host:" + serverName);
                    } catch (IOException e) {
                        System.out.println("No I/O");
                    }

                    /* try server. */
                    try {
                        System.out.println("Starting Talk Server with port number: " + serverPortNumber);
                        BufferedReader stdIn = null;
                        BufferedReader in = null;
                        PrintWriter out = null;
                        String message;
                        Socket client = null;
                        ServerSocket server = null;
                        try {
                            server = new ServerSocket(serverPortNumber);
                            System.out.println("Server listening on port " + serverPortNumber);
                        } catch (IOException e) {
                            System.out.println("Could not listen on port " + serverPortNumber);
                            System.exit(-1);
                        }
                        try {
                            client = server.accept();
                            System.out.println("Server accepted connection from " + client.getInetAddress());
                        } catch (IOException e) {
                            System.out.println("Accept failed on port " + serverPortNumber);
                            System.exit(-1);
                        }
                        try {
                            stdIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        } catch (IOException e) {
                            System.out.println("Couldn't get an inputStream from the client");
                            System.exit(-1);
                        }
                        try {
                            out = new PrintWriter(client.getOutputStream(), true);
                            in = new BufferedReader(new InputStreamReader(System.in));
                        } catch (IOException e) {
                            System.out.println("Read and Write error.");
                            System.exit(-1);
                        }
                        try {
                            while (true) {
                                if (stdIn.ready()) {
                                    System.out.println("Client:" + stdIn.readLine());
                                }
                                if (in.ready()) {
                                    message = in.readLine();
                                    if (message.equals("STATUS")) {
                                        System.out.println("Port number is: " + serverPortNumber);
                                    } else {
                                        out.println(message);
                                    }
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Read failed");
                            System.exit(-1);
                        }
                    } catch (Exception e) {
                        System.out.println("Can\'t run client and server. Program error.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Program error.");
            }
        } else {
            System.out.println("Please give me a command. Go for \"_-help\" for more info.");
        }
    }
}
