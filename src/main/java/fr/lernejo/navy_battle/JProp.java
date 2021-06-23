package fr.lernejo.navy_battle;

public class JProp {
    public String id;
    public String url;
    public String message;

    public JProp(){
    }

    @Override
    public String toString(){
        return id + "\n" + url + "\n" + message;
    }
}
