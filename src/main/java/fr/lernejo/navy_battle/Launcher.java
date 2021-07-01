package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException, InterruptedException {
        int arg = Integer.parseInt(args[0]);
        StartServer st = new StartServer(arg);
        if (args.length == 2) {
            OtherInstance oth = new OtherInstance();
            oth.other(String.valueOf(args[0]), args[1]);
        }
    }
}
