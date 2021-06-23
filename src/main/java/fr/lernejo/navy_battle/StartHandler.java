package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StartHandler implements HttpHandler {

    private Port port;

    public StartHandler(Port port) {
        this.port = port;
    }

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

        if (jp.id.isBlank()){
            System.out.println("Error wrong JSon id");
            String body = "Bad request";
            exchange.sendResponseHeaders(400, body.length());
            throw new IOException();
        }
        if (jp.message.isBlank()){
            System.out.println("Error wrong JSon message");
            String body = "Bad request";
            exchange.sendResponseHeaders(400, body.length());
            throw new IOException();
        }
        if (jp.url.isBlank()){
            System.out.println("Error wrong JSon url");
            String body = "Bad request";
            exchange.sendResponseHeaders(400, body.length());
            throw new IOException();
        }
        UUID rd = UUID.randomUUID();
        jp.message = "May the best code win";
        jp.id = rd.toString();
        jp.url = "http://localhost:"+port.port;

        System.out.println(jp);

        String body = mapper.writeValueAsString(jp);
        exchange.sendResponseHeaders(202, body.length());

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
        catch (IOException ex) {
            System.err.println("IOException "+ ex.getMessage());
        }
    }
}
