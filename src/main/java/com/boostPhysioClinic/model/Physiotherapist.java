package com.boostPhysioClinic.model;

import java.util.List;

public class Physiotherapist {

    private String id;
    private String fullName;
    private String address;
    private String telephone;
    private List<String> expertise;
    private List<Treatment> timeTable;

    public Physiotherapist(String id,String fullName, List<String> expertise, String address, String telephone, List<Treatment> timeTable) {
        this.id = id;
        this.fullName = fullName;
        this.expertise = expertise;
        this.address = address;
        this.telephone = telephone;
        this.timeTable = timeTable;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> getExpertise() {
        return expertise;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public List<Treatment> getTimeTable() {
        return timeTable;
    }
}
