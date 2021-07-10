package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

class StartHandlerTest {

    @Test
    void tStart() throws IOException, InterruptedException {
        StartServer st = new StartServer(5678);
        HttpClient cl = HttpClient.newHttpClient();
        String body = "{\"id\": \"1\", \"url\": \"http://localhost:5678\", \"message\": \"I will crush you!\" }";


        HttpRequest rq = HttpRequest.newBuilder().uri(URI.create("http://localhost:5678/api/game/start"))
            .POST(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse rp = cl.send(rq, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(202, rp.statusCode());

        st.serv.stop(0);
    }
    /*
    @Test
    void FireTest() throws IOException, InterruptedException {
        StartServer st = new StartServer(5678);
        HttpClient cl = HttpClient.newHttpClient();
        String body = "{\"id\": \"1\", \"url\": \"http://localhost:5678\", \"message\": \"I will crush you!\" }";
        System.out.println("test");

        HttpRequest rq = HttpRequest.newBuilder().uri(URI.create("http://localhost:5678/api/game/start"))
            .POST(HttpRequest.BodyPublishers.ofString(body)).build();
        cl.send(rq, HttpResponse.BodyHandlers.ofString());
        System.out.println("test");

        String url = "http://localhost:".concat("5678").concat("/api/game/fire?cell=E5");
        HttpRequest req = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpResponse<String> response = cl.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("test");
        String body_rep = "{\"consequence\":\"sunk\", \"shipLeft\":\"true\"}";
        Assertions.assertEquals(202, response.statusCode());
        Assertions.assertEquals(body_rep, response.body());
        st.serv.stop(0);
    }*/

}
