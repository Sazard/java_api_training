package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) {
        try {
            int arg = Integer.parseInt(args[0]);
            HttpServer serv = HttpServer.create(new InetSocketAddress(arg), 0);
            serv.setExecutor(Executors.newSingleThreadExecutor());
            serv.createContext("/ping", new RequestHandler());
            serv.start();

        }
        catch (java.io.IOException e){
            System.err.println("java.io.IOException " + e.getMessage());
        }
        /*
        catch (java.net.BindException a) {
            System.err.println("java.net.BindException " + a.getMessage());
        }*/

    }
}
