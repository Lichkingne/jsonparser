package it.mirea.jsonparser;

public class AirportFlights {



    private String time="";
    private String city="";
    private String planeid="";
    private String status="";


    public AirportFlights(String s, String time, String city, String planeid, String status) {
        this.time = time;
        this.city = city;
        this.planeid = planeid;
        this.status = status;

    }

    public AirportFlights(String time, String city, String planeid, String status) {
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

