package com.boostPhysioClinic.model;


import com.boostPhysioClinic.enums.Status;

public class Treatment {
    private String id;
    private String name;
    private String dateTime;
    private Patient patient;
    private Status status;

    public Treatment(String id,String name, String dateTime) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.patient = null;
        this.status = Status.AVAILABLE;
    }

    public void cancel() {
        if (this.status == Status.BOOKED) {
            this.status = Status.CANCELLED;
        }
    }

    public void attend() {
        if (this.status == Status.BOOKED) {
            this.status = Status.ATTENDED;
        }
    }


    public String getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getName() {
        return name;
    }

    public Patient getPatient() {
        return patient;
    }

    public void book(Patient patient) {
        if (this.status.equals(Status.AVAILABLE)) {
            this.patient = patient;
            this.status = Status.BOOKED;
        }
    }


    public Status getStatus() {
        return status;
    }
}
