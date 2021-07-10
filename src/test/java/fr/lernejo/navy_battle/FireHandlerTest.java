package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


class FireHandlerTest {

    @Test
    void FireH() throws IOException, InterruptedException {
        StartServer st = new StartServer(9876);
        HttpClient cl = HttpClient.newHttpClient();

        HttpRequest rq = HttpRequest.newBuilder().uri(URI.create("http://localhost:9876/api/game/fire?cell=F8")).GET().build();
        HttpResponse rp = cl.send(rq, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals("{\"consequence\":\"sunk\", \"shipLeft\":\"true\"}", rp.body());
        Assertions.assertEquals(202, rp.statusCode());

        st.serv.stop(0);
    }

    @Test
    void FireOut() throws IOException, InterruptedException {
        StartServer serv = new StartServer(8887);
        HttpClient cli = HttpClient.newHttpClient();

        HttpRequest req = HttpRequest.newBuilder().uri(URI.create("http://localhost:8887/api/game/fire?cell=F11")).GET().build();
        HttpResponse rep = cli.send(req, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals("Bad Request", rep.body());
        Assertions.assertEquals(400, rep.statusCode());


        serv.serv.stop(0);
    }
}
