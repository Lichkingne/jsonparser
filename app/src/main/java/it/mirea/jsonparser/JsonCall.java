package it.mirea.jsonparser;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonCall {
    private String departure;

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public List<JsonCall> getThread() {
        return thread;
    }

    public void setThread(List<JsonCall> thread) {
        this.thread = thread;
    }

    @JsonProperty("thread")
    private List<JsonCall> thread;



}




