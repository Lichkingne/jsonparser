package it.mirea.jsonparser;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AirportFlightsArrival {
    public String timeArr="";
    public String cityArr="";
    public String planeidArr="";
    public String statusArr="";


    public String getTimeArr() {
        return timeArr;
    }

    public String getCityArr() {
        return cityArr;
    }

    public String getPlaneidArr() {
        return planeidArr;
    }

    public String getStatusArr() {
        return statusArr;
    }

    public AirportFlightsArrival(String time, String city, String planeid, String status) {
        this.timeArr = time;
        this.cityArr = city;
        this.planeidArr = planeid;
        this.statusArr = status;

    }



    public void setTime(String time) {
        this.timeArr = time;
    }

    public void setCity(String city) {
        this.cityArr = city;
    }

    public void setPlaneid(String planeid) {
        this.planeidArr = planeid;
    }

    public void setStatus(String status) {
        this.statusArr = status;
    }


}







