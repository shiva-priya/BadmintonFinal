package com.badminton.Modules;

import java.sql.ResultSet;

public class Tournament {
    String name;
    String winPrize;
    String  runprize;
    String location;

    public Tournament() {
    }

    String empId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWinPrize() {
        return winPrize;
    }

    public void setWinPrize(String winPrize) {
        this.winPrize = winPrize;
    }

    public String getRunprize() {
        return runprize;
    }

    public void setRunprize(String runprize) {
        this.runprize = runprize;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "{" +
                " \"name\" : \"" + name + "\"" +
                ", \"winPrize\" : \"" + winPrize + "\"" +
                ", \"runprize\" : \"" + runprize + "\""  +
                ", \"location\" : \"" + location + "\"" +
                ", \"empId\" : \"" + empId + "\"" +
                "}";
    }

    public Tournament createTour(ResultSet res){
        Tournament tour = new Tournament();
        System.out.println("in create tour ");
        try {
            tour.setName(res.getString("name"));
            tour.setWinPrize(res.getString("winPrize"));
            tour.setRunprize(res.getString("runprize"));
            tour.setLocation(res.getString("location"));
            tour.setEmpId(res.getString("empId"));
        }
        catch (Exception e){
            e.printStackTrace();
            //player = null;
        }
        return tour;
    }
}
