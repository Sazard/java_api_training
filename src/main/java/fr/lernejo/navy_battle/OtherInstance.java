package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OtherInstance {

    public void other(int port, String url) {
        System.out.println("other serveur launched");
        String adversaryUrl = url;
        HttpClient cl = HttpClient.newHttpClient();

        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + url + "\", \"message\":\"hello\"}"))
            .build();
        try{
            cl.send(requetePost, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException | InterruptedException ie) {
            System.err.println("send failled");
        }
    }
}
