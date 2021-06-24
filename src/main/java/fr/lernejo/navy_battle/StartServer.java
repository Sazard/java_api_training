package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class StartServer {
    final HttpServer serv;
    StartServer(int port) throws IOException {
        this.serv = HttpServer.create(new InetSocketAddress(port), 0);
        this.serv.setExecutor(Executors.newSingleThreadExecutor());
        this.serv.createContext("/ping", new RequestHandler());
        this.serv.createContext("/api/game/start", new StartHandler(String.valueOf(port)));
        this.serv.createContext("/api/game/fire", new FireHandler());
        this.serv.start();
    }

}
