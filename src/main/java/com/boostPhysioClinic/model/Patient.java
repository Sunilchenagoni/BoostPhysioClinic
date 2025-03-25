package com.boostPhysioClinic.model;

import java.util.UUID;

public class Patient {
    private String id;
    private String fullName;
    private String address;
    private String telephone;

    public Patient(String fullName, String address, String telephone) {
        this.id = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.address = address;
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }
}
