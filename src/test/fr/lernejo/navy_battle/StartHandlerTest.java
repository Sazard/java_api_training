package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class StartHandlerTest {

    @Test
    void tStart() throws IOException, InterruptedException {
        StartServer st = new StartServer(9876);
        HttpClient cl = HttpClient.newHttpClient();
        String body = "{\"id\": \"1\", \"url\": \"http://localhost:8795\", \"message\": \"I will crush you!\" }";


        HttpRequest rq = HttpRequest.newBuilder().uri(URI.create("http://localhost:9876/api/game/start"))
            .POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse rp = cl.send(rq, HttpResponse.BodyHandlers.ofString());


        Assertions.assertEquals("{\"id\":\"2\", \"url\":\"http://localhost:9876\", \"message\":\"May the best code win\"}", rp.body());
        Assertions.assertEquals(202, rp.statusCode());

        st.serv.stop(0);
    }

}
