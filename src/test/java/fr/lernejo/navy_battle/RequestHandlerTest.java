package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

class RequestHandlerTest {

    @Test
    void ping() throws IOException, InterruptedException {
        StartServer st = new StartServer(1234);
        HttpClient cl = HttpClient.newHttpClient();

        HttpRequest rq = HttpRequest.newBuilder().uri(URI.create("http://localhost:1234/ping")).GET().build();
        HttpResponse rp = cl.send(rq, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals("OK", rp.body());
        Assertions.assertEquals(200, rp.statusCode());

        st.serv.stop(0);
    }

}
