package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;



public class StartHandler implements HttpHandler {
    /*
    puis tu verifie que les donnees qu on t envois sont bonne
    tu creer ton json de reponse
    puis ton handler le transforme en requette
    le send*/
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals("POST")) {
            throw new IOException();
        }
        InputStream s = exchange.getRequestBody();
        ObjectMapper mapper = new ObjectMapper();
        JProp jp;

        int c;
        StringBuilder builder = new StringBuilder();
        while ((c = exchange.getRequestBody().read()) > 0) {
            builder.append((char) c);
        }

        try {
            jp = mapper.readValue(builder.toString(), JProp.class);
        }
        catch (IOException ie) {
            System.out.println("Error mapper fail, wrong JSon");
            String body = "Bad request";
            exchange.sendResponseHeaders(400, body.length());
            throw new IOException();
        }
        /*
        System.out.println("Id found : " + jp.id);
        System.out.println("Message found : "+jp.message);
        System.out.println("Url found : " +jp.url);


        System.out.println("success");
        */
        String body = builder.toString();

        exchange.sendResponseHeaders(202, body.length());

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
        catch (IOException ex) {
            System.err.println("IOException "+ ex.getMessage());
        }
    }
}
