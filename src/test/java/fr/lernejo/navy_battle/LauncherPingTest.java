package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class LauncherPingTest {

    @Test
    void tStartServ() throws IOException, InterruptedException {
        StartServer st = new StartServer(5678);
        HttpClient cl = HttpClient.newHttpClient();

        HttpRequest rq = HttpRequest.newBuilder().uri(URI.create("http://localhost:5678/ping"))
            .GET().build();

        HttpResponse rp = cl.send(rq, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(rp.body(), "OK");
        Assertions.assertEquals(rp.statusCode(), 200);
        st.serv.stop(0);
    }

}
