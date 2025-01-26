package theVaultHunter0;

import theVaultHunter0.Header.Header;
import theVaultHunter0.Server.DefaultHeaders;
import theVaultHunter0.Server.HttpServerF;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, IllegalAccessException {
        boolean startServer = false;
        int PORT = 5050;

        if(startServer){
            HttpServerF server = new HttpServerF();
            server.startServer(PORT);
            System.out.println(server.getAddrString());
        }

        DefaultHeaders.addSectionToResponseDefault("Security");
        DefaultHeaders.addParameterToResponseDefault("security" , "Authorization", "TOKEN");
        System.out.print(DefaultHeaders.getResponseHeader().toString());
    }
}