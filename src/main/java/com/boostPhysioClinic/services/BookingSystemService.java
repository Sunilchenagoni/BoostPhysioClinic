package com.boostPhysioClinic.services;


import com.boostPhysioClinic.enums.Status;
import com.boostPhysioClinic.interfaces.BookingSystem;
import com.boostPhysioClinic.model.Patient;
import com.boostPhysioClinic.model.Physiotherapist;
import com.boostPhysioClinic.model.Treatment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookingSystemService implements BookingSystem {

    private List<Physiotherapist> physiotherapists = new ArrayList<Physiotherapist>();
    private List<Patient> patients = new ArrayList<>();

    {
        Physiotherapist physio1 = new Physiotherapist("1", "Dr. Alice",
                Arrays.asList("Physiotherapy", "Rehabilitation"),
                "123 Health St", "123456789",
                new ArrayList<>(List.of(
                        // Week 1
                        new Treatment("1", "Neural Mobilisation", "Monday 1st May 2025, 10:00-11:00"),
                        new Treatment("2", "Acupuncture", "Wednesday 3rd May 2025, 14:00-15:00"),

                        // Week 2
                        new Treatment("3", "Massage", "Friday 8th May 2025, 09:00-10:00"),
                        new Treatment("4", "Pool Rehabilitation", "Saturday 10th May 2025, 12:00-13:00"),

                        // Week 3
                        new Treatment("5", "Mobilisation of the spine and joints", "Monday 15th May 2025, 11:00-12:00"),
                        new Treatment("6", "Acupuncture", "Thursday 17th May 2025, 13:00-14:00"),

                        // Week 4
                        new Treatment("7", "Massage", "Tuesday 22nd May 2025, 09:00-10:00"),
                        new Treatment("8", "Neural Mobilisation", "Friday 24th May 2025, 14:00-15:00")
                )));

        Physiotherapist physio2 = new Physiotherapist("2", "Dr. Bob",
                Arrays.asList("Osteopathy", "Manual Therapy"),
                "456 Therapy Ave", "987654321",
                new ArrayList<>(List.of(
                        // Week 1
                        new Treatment("1", "Mobilisation of the spine and joints", "Tuesday 2nd May 2025, 09:00-10:00"),
                        new Treatment("2", "Massage", "Thursday 4th May 2025, 16:00-17:00"),

                        // Week 2
                        new Treatment("3", "Neural Mobilisation", "Monday 8th May 2025, 11:00-12:00"),
                        new Treatment("4", "Acupuncture", "Wednesday 10th May 2025, 10:00-11:00"),

                        // Week 3
                        new Treatment("5", "Pool Rehabilitation", "Friday 15th May 2025, 13:00-14:00"),
                        new Treatment("6", "Massage", "Sunday 17th May 2025, 15:00-16:00"),

                        // Week 4
                        new Treatment("7", "Mobilisation of the spine and joints", "Tuesday 22nd May 2025, 10:00-11:00"),
                        new Treatment("8", "Pool Rehabilitation", "Saturday 25th May 2025, 09:00-10:00")
                )));

        Physiotherapist physio3 = new Physiotherapist("3", "Dr. Charlie",
                Arrays.asList("Rehabilitation", "Physiotherapy", "Osteopathy"),
                "789 Recovery Rd", "555123456",
                new ArrayList<>(List.of(
                        // Week 1
                        new Treatment("1", "Pool Rehabilitation", "Monday 1st May 2025, 12:00-13:00"),
                        new Treatment("2", "Acupuncture", "Thursday 3rd May 2025, 15:00-16:00"),

                        // Week 2
                        new Treatment("3", "Neural Mobilisation", "Tuesday 8th May 2025, 10:00-11:00"),
                        new Treatment("4", "Massage", "Friday 10th May 2025, 14:00-15:00"),

                        // Week 3
                        new Treatment("5", "Mobilisation of the spine and joints", "Wednesday 15th May 2025, 11:00-12:00"),
                        new Treatment("6", "Acupuncture", "Thursday 17th May 2025, 13:00-14:00"),

                        // Week 4
                        new Treatment("7", "Pool Rehabilitation", "Monday 22nd May 2025, 09:00-10:00"),
                        new Treatment("8", "Neural Mobilisation", "Friday 24th May 2025, 15:00-16:00")
                )));

        Physiotherapist physio4 = new Physiotherapist("4", "Dr. Daniel",
                Arrays.asList("Rehabilitation", "Osteopathy"),
                "567 Wellness Blvd", "444555666",
                new ArrayList<>(List.of(
                        // Week 1
                        new Treatment("1", "Massage", "Tuesday 2nd May 2025, 10:00-11:00"),
                        new Treatment("2", "Acupuncture", "Thursday 4th May 2025, 14:00-15:00"),

                        // Week 2
                        new Treatment("3", "Neural Mobilisation", "Monday 8th May 2025, 09:00-10:00"),
                        new Treatment("4", "Mobilisation of the spine and joints", "Wednesday 10th May 2025, 12:00-13:00"),

                        // Week 3
                        new Treatment("5", "Pool Rehabilitation", "Friday 15th May 2025, 14:00-15:00"),
                        new Treatment("6", "Massage", "Sunday 17th May 2025, 16:00-17:00"),

                        // Week 4
                        new Treatment("7", "Mobilisation of the spine and joints", "Tuesday 22nd May 2025, 10:00-11:00"),
                        new Treatment("8", "Pool Rehabilitation", "Saturday 25th May 2025, 09:00-10:00")
                )));

        physiotherapists.add(physio1);
        physiotherapists.add(physio2);
        physiotherapists.add(physio3);
        physiotherapists.add(physio4);
    }


    // Add a new patient
    public String addPatient(Patient patient) {
        if (patients.stream().anyMatch(p -> p.getTelephone().equals(patient.getTelephone()))) {
            return "Patient already registered";
        }
        patients.add(patient);
        return "Registered Successfully";
    }

    // Remove a patient
    public String removePatient(String mobileNumber) {
        boolean removed = patients.removeIf(p -> p.getTelephone().equals(mobileNumber));
        if (removed) {
            return "Patient removed successfully";
        }
        return "Patient not found";
    }

    // Book a treatment
    public String bookTreatment(String mobileNumber, String physioId, String treatmentId, String expert) {
        Patient patient = patients.stream()
                .filter(p -> p.getTelephone().equals(mobileNumber))
                .findFirst()
                .orElse(null);
        Physiotherapist physiotherapist ;
        if(Objects.nonNull(expert)){
            List<Physiotherapist> list = physiotherapists.stream().filter(p->p.getExpertise().contains(expert) && p.getId().equals(physioId)).toList();
            if(list.isEmpty()) {
                return "Expertises not found";
            }
            physiotherapist=list.get(0);
        }
        else{
            physiotherapist = this.findPhysiotherapistById(physioId);
        }

        if (patient == null) return "Patient not found";
        if (physiotherapist == null) return "Physiotherapist not found";

        // Find treatment by ID
        for (Treatment t : physiotherapist.getTimeTable()) {
            if (t.getId().equals(treatmentId) && Status.AVAILABLE.equals(t.getStatus())) {
                t.book(patient);
                return "Treatment Booked Successfully";
            } else if (t.getId().equals(treatmentId) && !Status.AVAILABLE.equals(t.getStatus())) {
                return "Treatment already booked";
            }
        }
        return "Treatment not found for this physiotherapist";
    }

    // Cancel a booking
    public String cancelBooking(String physioId, String treatmentId) {
        Physiotherapist physiotherapist = findPhysiotherapistById(physioId);
        if (physiotherapist == null) return "Physiotherapist not found";

        for (Treatment t : physiotherapist.getTimeTable()) {
            if (t.getId().equals(treatmentId) && Status.BOOKED.equals(t.getStatus())) {
                t.cancel();
                return "Treatment Canceled Successfully";
            }
        }
        return "Please Book Treatment";
    }

    // Attend a treatment
    public String attendTreatment(String physioId, String treatmentId) {
        Physiotherapist physiotherapist = findPhysiotherapistById(physioId);
        if (physiotherapist == null) return "Physiotherapist not found";

        for (Treatment t : physiotherapist.getTimeTable()) {
            if (t.getId().equals(treatmentId) && Status.BOOKED.equals(t.getStatus())) {
                t.attend();
                return "Attended for the treatment";
            }
        }
        return "Please Book Treatment";
    }

    // Generate Report
    public void printReport() {
        System.out.println("\n==== Clinic Report ====");
        for (Physiotherapist physio : physiotherapists) {
            System.out.println("\nPhysiotherapist: " + physio.getFullName());
            for (Treatment t : physio.getTimeTable()) {
                String patientName = Objects.nonNull(t.getPatient()) ? t.getPatient().getFullName() : "N/A";
                System.out.println("Treatment: " + t.getName() + " | Patient: " + patientName + " | Time: " + t.getDateTime() + " | Status: " + t.getStatus());
            }
        }

        // Sorting physiotherapists by attended appointments
        List<Physiotherapist> sortedPhysios = physiotherapists.stream().sorted((p1, p2) -> Long.compare(p2.getTimeTable().stream().filter(t -> Status.ATTENDED.equals(((Treatment) t).getStatus())).count(), p1.getTimeTable().stream().filter(t -> Status.ATTENDED.equals(((Treatment) t).getStatus())).count())).collect(Collectors.toList());

        System.out.println("\n==== Physiotherapist Ranking (by attended appointments) ====");
        for (Physiotherapist p : sortedPhysios) {
            long attendedCount = p.getTimeTable().stream().filter(t -> Status.ATTENDED.equals(t.getStatus())).count();
            System.out.println(p.getFullName() + " - " + attendedCount + " attended appointments");
        }
    }

    @Override
    public void showPhysiotherapists() {
        if (physiotherapists.isEmpty()) {
            System.out.println("No physiotherapists available.");
            return;
        }

        System.out.println("\n========================================================================================================================= Physiotherapists ==========================================================================================");
        System.out.printf("| %-10s | %-20s | %-12s | %-50s | %-15s | %-50s | %-50s |\n",
                "ID", "Name", "Phone", "Expertise", "Treatment ID", "Treatment Name", "Slot");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        boolean foundAvailableTreatment = false; // Track if any available treatments exist

        for (Physiotherapist physio : physiotherapists) {
            List<Treatment> availableTreatments = physio.getTimeTable().stream()
                    .filter(t -> t.getStatus().equals(Status.AVAILABLE))
                    .toList(); // Get only available treatments

            if (!availableTreatments.isEmpty()) {
                foundAvailableTreatment = true; // At least one treatment found

                // Print the first available treatment along with physiotherapist details
                Treatment firstTreatment = availableTreatments.get(0);
                System.out.printf("| %-10s | %-20s | %-12s | %-50s | %-15s | %-50s | %-50s |\n",
                        physio.getId(),
                        physio.getFullName(),
                        physio.getTelephone(),
                        String.join(", ", physio.getExpertise()),
                        firstTreatment.getId(),
                        firstTreatment.getName(),
                        firstTreatment.getDateTime()
                );

                // Print additional available treatments without repeating physiotherapist info
                for (int i = 1; i < availableTreatments.size(); i++) {
                    Treatment t = availableTreatments.get(i);
                    System.out.printf("| %-10s | %-20s | %-12s | %-50s | %-15s | %-50s | %-50s |\n",
                            "", "", "", "",
                            t.getId(),
                            t.getName(),
                            t.getDateTime()
                    );
                }
            }

        }
        if (!foundAvailableTreatment) {
            System.out.println("| No physiotherapists with available treatments found. |");
        }

        System.out.println("=====================================================================================================================================================================================================================================");
    }

    @Override
    public void showPhysiotherapists(String expertise) {
        if (Objects.isNull(expertise) || expertise.trim().isEmpty()) {
            System.out.println("Expertise should not be null or empty.");
            return;
        }

        System.out.println("\n========================================================================================================================= Physiotherapists ==========================================================================================");

        System.out.printf("| %-10s | %-20s | %-12s | %-50s | %-15s | %-50s | %-50s |\n",
                "ID", "Name", "Phone", "Expertise", "Treatment ID", "Treatment Name", "Slot");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        boolean foundAvailableExpertise = false; // Track if we found a matching physiotherapist

        for (Physiotherapist physio : physiotherapists) {
            boolean hasMatchingExpertise = physio.getExpertise().stream()
                    .anyMatch(e -> e.equalsIgnoreCase(expertise)); // Case-insensitive match

            if (hasMatchingExpertise) {
                List<Treatment> availableTreatments = physio.getTimeTable().stream()
                        .filter(t -> t.getStatus().equals(Status.AVAILABLE))
                        .toList(); // Get only available treatments

                if (!availableTreatments.isEmpty()) {
                    foundAvailableExpertise = true;

                    // Print the first available treatment along with physiotherapist details
                    Treatment firstTreatment = availableTreatments.get(0);
                    System.out.printf("| %-10s | %-20s | %-12s | %-50s | %-15s | %-50s | %-50s |\n",
                            physio.getId(),
                            physio.getFullName(),
                            physio.getTelephone(),
                            String.join(", ", physio.getExpertise()),
                            firstTreatment.getId(),
                            firstTreatment.getName(),
                            firstTreatment.getDateTime()
                    );

                    // Print additional available treatments without repeating physiotherapist info
                    for (int i = 1; i < availableTreatments.size(); i++) {
                        Treatment t = availableTreatments.get(i);
                        System.out.printf("| %-10s | %-20s | %-12s | %-50s | %-15s | %-50s | %-50s |\n",
                                "", "", "", "",
                                t.getId(),
                                t.getName(),
                                t.getDateTime()
                        );
                    }
                }
            }
        }

        if (!foundAvailableExpertise) {
            System.out.println("| No physiotherapists found with expertise in '" + expertise + "' or no available treatments. |");
        }

        System.out.println("=====================================================================================================================================================================================================================================");
    }

    // Helpers
    private Patient findPatientById(String id) {
        return patients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private Physiotherapist findPhysiotherapistById(String id) {
        return physiotherapists.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
