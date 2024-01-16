package com.example.citycard;

import java.io.Serializable;
import java.util.List;

public class Schedules implements Serializable {  //this is the class where schedule objects that we fetch from Firebase is kept
    private String busName;
    private List<String> schedule;    //these variables match with how they are in Firebase

    private String departure;
    private String destination;


    public Schedules() {
    }

    public Schedules(String busName, List<String> schedule, String departure, String destination) {
        this.busName = busName;
        this.schedule = schedule;
        this.departure=departure;
        this.destination=destination;

    }

    public String getBusName() {
        return busName;
    }

    public List<String> getSchedule() {
        return schedule;
    }
    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

}