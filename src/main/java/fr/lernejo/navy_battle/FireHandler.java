package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class FireHandler implements HttpHandler {
    /*
    private final String cell;
    public FireHandler(String cell) {
        this.cell = cell;
    }*/

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals("GET")) {
            throw new IOException();
        }
        ObjectMapper mapper = new ObjectMapper();
        String body = "{\"consequence\":\"sunk\", \"shipLeft\":\"true\"}";

        exchange.sendResponseHeaders(202, body.length());

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
        catch (IOException ex) {
            System.err.println("IOException "+ ex.getMessage());
        }

    }
}
