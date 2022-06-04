package it.mirea.jsonparser;
public class AirportFlights {
    public String time="";
    public String city="";
    public String planeid="";
    public String status="";
    public String getTime() {
        return time;
    }
    public String getCity() {
        return city;
    }
    public String getPlaneid() {
        return planeid;
    }
    public String getStatus() {
        return status;
    }
    public AirportFlights(String time, String city, String planeid, String status) {
        this.time = time;
        this.city = city;
        this.planeid = planeid;
        this.status = status;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setPlaneid(String planeid) {
        this.planeid = planeid;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    }

