package theVaultHunter0.Client;

import theVaultHunter0.Server.HttpServerF;

import java.io.*;
import java.net.Socket;

public class Client {
    private static int c = 0;
    private int id;
    private Thread threadClient;
    private Socket socketClient;

    public Client(Socket socket) throws IOException, InterruptedException {
        id = c;
        c++;
        socketClient = socket;
    }

    public void threadCaller() throws IOException, InterruptedException {
        threadClient = new Thread(() -> {
            try {
                startClient();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        threadClient.start();
        threadClient.join();
    }

    public void startClient() throws IOException, InterruptedException {
        InputStream input = socketClient.getInputStream();
        OutputStream output = socketClient.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String in = reader.readLine();
        if(in != null) {
            System.out.println(in);
        }
        String exemple =
                "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/plain\r\n" +
                "Content-Length: 13\r\n" +
                "\r\n" +
                "Hello!";
        output.write(exemple.getBytes());
        output.flush();
        output.close();
        socketClient.close();
    }

    public Thread getThread(){
        return threadClient;
    }

    public Socket getSocketClient(){
        return socketClient;
    }
}
