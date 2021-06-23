package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) {
        try {
            int arg = Integer.parseInt(args[0]);
            Port p = new Port();
            p.port = String.valueOf(arg);

            if (args.length == 2) {
                String argUrl = args[1].toString();
                OtherInstance oth = new OtherInstance();
                oth.other(arg, argUrl);
            }
            else {
                HttpServer serv = HttpServer.create(new InetSocketAddress(arg), 0);
                serv.setExecutor(Executors.newSingleThreadExecutor());
                serv.createContext("/ping", new RequestHandler());
                serv.createContext("/api/game/start", new StartHandler(p));
                serv.start();
            }

        }
        catch (java.io.IOException e){
            System.err.println("java.io.IOException " + e.getMessage());
        }

    }
}
