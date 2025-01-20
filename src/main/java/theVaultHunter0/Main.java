package theVaultHunter0;

import theVaultHunter0.Header.Header;
import theVaultHunter0.Server.HttpServerF;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        int PORT = 5050;

        HttpServerF server = new HttpServerF();
        server.startServer(PORT);
        System.out.println(server.getAddrString());
    }
}