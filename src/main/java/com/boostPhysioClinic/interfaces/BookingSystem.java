package com.boostPhysioClinic.interfaces;

import com.boostPhysioClinic.model.Patient;

public interface BookingSystem {

    String addPatient(Patient patient);

    String removePatient(String patientId);

    String bookTreatment(String patientId, String physioId, String treatmentId, String expert);

    String cancelBooking(String physioId, String treatmentId);

    String attendTreatment(String physioId, String treatmentId);

    void printReport();

    void showPhysiotherapists();

    void showPhysiotherapists(String expertise);
}
