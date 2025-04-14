package theVaultHunter0;

import theVaultHunter0.Server.HttpServerF;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, IllegalAccessException, InvocationTargetException {
        boolean startServer = true;
        int PORT = 5050;

        if(startServer){
            HttpServerF server = new HttpServerF();
            server.startServer(PORT);
        }

        //DefaultHeaders.addSectionToResponseDefault("Security");
        //DefaultHeaders.addParameterToResponseDefault("security" , "Authorization", "TOKEN");
        //System.out.print(DefaultHeaders.getResponseHeader().toString());

        //System.out.println(DefaultHeaders.getResponseHeader().getParameterToSection());

        String httpRequest =
            "POST /api/v1/resource HTTP/1.1\r\n" +
            "Host: example.com\r\n" +
            "User-Agent: MyClient/1.0\r\n" +
            "Content-Type: application/json\r\n" +
            "Authorization: Bearer your_token_here\r\n" +
            "Accept: application/json\r\n" +
            "Content-Length: 52\r\n" +
                    "customcustom : 69\r\n" +
            "\r\n" +
            "{ \"name\": \"John Doe\", \"email\": \"johndoe@example.com\" }";

        Http http = Http.fromString(httpRequest);
        System.out.println(http.getHeader());
    }
}