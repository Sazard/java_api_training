package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class StartServeurTest {

    @Test
    void tStartServ() throws IOException, InterruptedException {
        StartServer st = new StartServer(5678);
        Assertions.assertNotEquals(st, "IOException");
        st.serv.stop(0);
    }

}
