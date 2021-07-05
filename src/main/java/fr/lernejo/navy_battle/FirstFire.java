package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class FirstFire {

    private final String port;
    public FirstFire(String port) {
        this.port = port;
    }

    public void ff() throws IOException, InterruptedException {
        HttpClient cl = HttpClient.newHttpClient();
        String url = "http://localhost:".concat(port).concat("/api/game/fire?cell=E5");
        HttpRequest req = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpResponse<String> response = cl.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
