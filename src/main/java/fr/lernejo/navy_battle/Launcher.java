package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) {
        try {
            int arg = Integer.parseInt(args[0]);
            String port = String.valueOf(arg);

            if (args.length == 2) {
                OtherInstance oth = new OtherInstance();
                oth.other(String.valueOf(args[0]), args[1].toString());
            }
            else {
                StartServer st = new StartServer(arg);
                //add serv.stop
            }

        }
        catch (IOException | InterruptedException e){
            System.err.println("java.io.IOException " + e.getMessage());
        }

    }
}
