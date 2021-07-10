package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StartHandler implements HttpHandler {

    private final String port;
    public StartHandler(String port) {
        this.port = port;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals("POST")) {
            bad_requ(exchange);
        }
        JProp jp = fill(exchange);

        condition(jp, exchange);

        resp(exchange);
        FirstFire ff = new FirstFire(String.valueOf(URI.create(jp.url).getPort()));
        try {
            ff.ff();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void resp(HttpExchange exchange) throws IOException {
        String body = "{\n\t\"id\":\"" + UUID.randomUUID() + ("\",\n\t" + " \"url\":\"http://localhost:").concat(port).concat("\",\n\t \"message\":\"May the best code win\"\n}");
        exchange.sendResponseHeaders(202, body.length());
        OutputStream os = exchange.getResponseBody();
        os.write(body.getBytes());
    }

    private void condition(JProp jp, HttpExchange exchange) throws IOException {
        if (jp.id.isBlank() | jp.message.isBlank() | jp.url.isBlank()){
            bad_requ(exchange);
        }
    }

    private JProp fill(HttpExchange exchange) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        int c;
        JProp jp = null;
        StringBuilder builder = new StringBuilder();
        while ((c = exchange.getRequestBody().read()) > 0) {
            builder.append((char) c);
        }
        try {
            jp = mapper.readValue(builder.toString(), JProp.class);
        }
        catch (IOException ie) {
            bad_requ(exchange);
        }
        return jp;
    }

    private void bad_requ(HttpExchange exchange) throws IOException {
        String body = "Bad Request";
        exchange.sendResponseHeaders(400, body.length());
        OutputStream os = exchange.getResponseBody();
        os.write(body.getBytes());
        throw new IOException();
    }
}
