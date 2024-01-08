package com.example.citycard;

import java.util.List;

public class Schedules {
    private String routeName;
    private List<String> schedule;

    public Schedules(String routeName, List<String> schedule) {
        this.routeName = routeName;
        this.schedule = schedule;
    }

    public String getRouteName() {
        return routeName;
    }

    public List<String> getSchedule() {
        return schedule;
    }
}