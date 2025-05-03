package theVaultHunter0.Client;

import theVaultHunter0.Default.DefaultHeaders;
import theVaultHunter0.Http;
import theVaultHunter0.Server.HttpServerF;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.UUID;

public class Client {
    private UUID id;
    private Thread threadClient;
    private Socket socketClient;

    public Client(Socket socket) throws IOException, InterruptedException {
        id = UUID.randomUUID();
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
        HttpServerF.removeClients(this.id);
    }

    //HERE THE THREAD OF THE CLIENT READ ON THE SOCKET AND WAIT FOR INCOMMING.
    //NEED TO MODIFY SO THAT IT WILL CREATE FROM THE INPUT A HTTP OBJECT.
    //NEED TO COMPARE IF DEFAULTHEADER EXIST
    //CHECK SECURITY ETC...
    private void startClient() throws IOException, InterruptedException {
        InputStream input = socketClient.getInputStream();
        OutputStream output = socketClient.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String in = reader.readLine();
        //From what was read by the bufferReader create the HTTP object
        Http request;
        try{
            request = Http.fromString(in);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println("For client:" + id + "error in making HTTP object.");
            //
            //TODO CREATE A ERROR MESSAGE CLOSE THE OUTPUT AND SOCKET.
            //
            socketClient.close();
            throw new RuntimeException(e);
        }
        //IF A DEFAULT REQUEST HAS BEEN MADE
        if(!DefaultHeaders.isRequestDefaultEmpty()){
            //
            //TODO CREATE FUNCTION THAT WILL COMPARE THE DEFAULT REQUEST HEADER AND THE RECEIVE HEADER
            // - IF NO MATCH SEND BACK A ERROR MESSAGE
            // - IF MATCH CONTINUE...
            //
            socketClient.close();
        }
        System.out.println(request);
        sendOutput(output, "Good job!");
        socketClient.close();
    }

    public Thread getThread(){
        return threadClient;
    }

    public Socket getSocketClient(){
        return socketClient;
    }

    public UUID getId(){
        return id;
    }

    public void sendOutput(OutputStream output, String data) {
        try {
            String response =
                    "HTTP/1.1 200 OK\r\n"  +
                            "\r\n" +
                            data;
            output.write(response.getBytes());
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
