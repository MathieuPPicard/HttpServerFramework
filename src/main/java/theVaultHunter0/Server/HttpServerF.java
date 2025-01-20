package theVaultHunter0.Server;

import theVaultHunter0.Client.Client;

import javax.sound.sampled.Port;
import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class HttpServerF {

    private final boolean USE_LOCAL_HOST = true;
    private final String IP_ADDR = "0.0.0.0";
    private final int BACKLOG = 10;
    private int PORT;

    private static ServerSocket serverSocket;
    private static ArrayList<Client> clients;
    private static InetAddress address;
    private static boolean running = true;

    public void startServer(int port) throws IOException, InterruptedException {
        PORT = port;
        System.out.println("Configuration...");
        if(USE_LOCAL_HOST) {
            address = InetAddress.getByName("127.0.0.1");
        } else{
            address = InetAddress.getByName(IP_ADDR);
        }
        clients = new ArrayList<>();
        serverSocket = new ServerSocket(port,BACKLOG, address);

        Thread conThread = new Thread(() -> {
            try {
                conThread();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        conThread.start();

        Thread closingThread = new Thread(() -> {
            try {
                closingThread();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        closingThread.start();

        System.out.println("Server ready, to stop enter 'Q' or 'q'.");

        closingThread.join();
        conThread.join();

        System.out.println("Server stopped");
    }

    //Connexion thread, will create Client and Socket object to create a Thread for every client
    private static void conThread() throws IOException, InterruptedException {
        while(running){
            try{
                Socket socket = serverSocket.accept();
                Client client = new Client(socket);
                clients.add(client);
                client.threadCaller();

            } catch (SocketException e) {
                if(!running)
                {
                    break;
                }
            }
        }

        //When the server is shutdown, wait for all clients to join.
        if(!clients.isEmpty()) {
            for(Client client : clients){
                client.getThread().join();
            }
            System.out.println("All client thread were join.");
        }
        System.out.println("All client thread were already joined.");
    }

    //Simple thread to shut down the server via the terminal
    private static void closingThread() throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        while(running){
            String strScan = scan.nextLine();
            if(strScan.equals("q") || strScan.equals("Q")){
                running = false;
                serverSocket.close();
            }
        }
        Thread.sleep(100);
    }

    //Return the addresse + port of the server
    public String getAddrString() {
        if(USE_LOCAL_HOST)
        {
            return "127.0.0.1" + ":" + PORT;
        }
        return IP_ADDR + ":" + PORT;
    }
}
