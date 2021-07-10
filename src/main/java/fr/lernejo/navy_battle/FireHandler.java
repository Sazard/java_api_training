package fr.lernejo.navy_battle;

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
        // .query
        String cell = exchange.getRequestURI().toString().substring(exchange.getRequestURI().toString().indexOf("?cell=") + 6);
        if (cell.charAt(0) == '\0' | (int) cell.charAt(0) > (int) 'J' | (int) cell.charAt(0) < (int) 'A' | Integer.parseInt(cell.substring(1)) > 10 | Integer.parseInt(cell.substring(1)) < 0) {
            String body = "Bad Request";
            exchange.sendResponseHeaders(400, body.length());
            OutputStream os = exchange.getResponseBody();
            os.write(body.getBytes());
        }
        String body = "{\"consequence\":\"sunk\", \"shipLeft\":\"true\"}";
        exchange.getResponseHeaders().set("Accept", "application/json");
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(202, body.length());
        OutputStream os = exchange.getResponseBody();
        os.write(body.getBytes());
    }
}
