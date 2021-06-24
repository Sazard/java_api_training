package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StartHandler implements HttpHandler {

    private final String port;
    public StartHandler(String port) {this.port = port;
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
            exchange.sendResponseHeaders(400, "Bad request".length());
            throw new IOException();
        }
        if (jp.id.isBlank() | jp.message.isBlank() | jp.url.isBlank()){
            exchange.sendResponseHeaders(400, "Bad request".length());
            throw new IOException();
        }
        String body = "{\"id\":\"1\", \"url\":\"http://localhost:".concat(port.toString()).concat("\", \"message\":\"May the best code win\"}");
        exchange.sendResponseHeaders(202, body.length());

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
        catch (IOException ex) {
            System.err.println("IOException "+ ex.getMessage());
        }
    }
}
