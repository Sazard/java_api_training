package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OtherInstance {

    public void other(String arg, String argUrl) throws IOException, InterruptedException {
        HttpClient cl = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create(argUrl + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + arg + "\", \"message\":\"hello\"}"))
            .build();
        HttpResponse response = cl.send(requetePost, HttpResponse.BodyHandlers.ofString());
    }
}
