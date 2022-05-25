package it.mirea.jsonparser;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Info {

    @JsonProperty("schedule")
    private List<JsonCall> schedule;

    public List<JsonCall> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<JsonCall> schedule) {
        this.schedule = schedule;
    }
}
