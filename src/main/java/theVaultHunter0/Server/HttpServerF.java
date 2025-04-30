package theVaultHunter0.Server;

import theVaultHunter0.Client.Client;
import theVaultHunter0.Default.DefaultHeaders;
import theVaultHunter0.Server.Checker.DefaultChecker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

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
                closingMainThread();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        closingThread.start();

        //Check if default was initialize
        Method defaultInitMethod = DefaultChecker.verifyDefault();
        if(defaultInitMethod != null){
            try{
                defaultInitMethod.invoke(null);
            } catch(InvocationTargetException | IllegalAccessException e){
                System.out.println("Invoking default init method failed");
                throw new RuntimeException(e);
            }
            System.out.println(DefaultHeaders.isRequestDefaultEmpty());
            System.out.println(DefaultHeaders.isResponseDefaultEmpty());
            System.out.println("Default header initialize.");
        }

        System.out.println("IP:SOCKET = " + this.getAddrString());
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
            for(int i = clients.size() - 1; i >= 0; i--){
                Client client = clients.remove(i);
                try {
                    client.getThread().join();
                    removeClients(client.getId());
                } catch (InterruptedException e) {
                    System.err.println("Error when joining threads : " + e);
                }

            }
            System.out.println("All client thread have joined.");
        }
        System.out.println("No client to join.");
    }

    //Simple thread to shut down the server via the terminal
    private static void closingMainThread() throws IOException, InterruptedException {
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

    //Remove and delete the clients
    public static void removeClients(UUID id){
        System.out.println("Before removing client : " + clients.toString());
        for(int i = 0; i < clients.size(); i++){
            if(clients.get(i).getId() == id){
                Client c = clients.remove(i);
                System.out.println("After removing client : " + clients.toString());
                return;
            }
        }
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
