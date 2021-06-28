package fr.lernejo.navy_battle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JProp {
    public final String id;
    public final String url;
    public final String message;

    JProp(
        @JsonProperty("id") String id,
        @JsonProperty("url") String url,
        @JsonProperty("message") String message){
        this.id = id;
        this.url = url;
        this.message = message;
    }
}
