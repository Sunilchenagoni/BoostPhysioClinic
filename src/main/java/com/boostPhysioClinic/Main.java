package com.boostPhysioClinic;

import com.boostPhysioClinic.interfaces.BookingSystem;
import com.boostPhysioClinic.model.Patient;
import com.boostPhysioClinic.services.BookingSystemService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookingSystem system = new BookingSystemService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n==== Boost Physio Clinic Booking System ====");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Patient");
            System.out.println("2. Remove Patient");
            System.out.println("3. Book Treatment");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Attend Treatment");
            System.out.println("6. Print Report");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Full Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine();

                    Patient newPatient = new Patient(name.trim(), address.trim(), phone.trim());
                    System.out.println(system.addPatient(newPatient));
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Enter Mobile Number to remove: ");
                    String mobile = scanner.nextLine();
                    System.out.println(system.removePatient(mobile.trim()));
                    break;

                case 3:
                    System.out.println("1. Show All Physiotherapists");
                    System.out.println("2. Show Physiotherapists Based on Expertise");
                    System.out.print("Enter the Choice: ");

                    int bookingChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume the leftover newline
                    String expertise=null;

                    switch (bookingChoice) {
                        case 1:
                            system.showPhysiotherapists();
                            break;
                        case 2:
                            System.out.print("Enter Expertise Name: ");
                            expertise = scanner.nextLine().trim();
                            system.showPhysiotherapists(expertise);
                            break;
                        default:
                            System.out.println("Invalid choice");
                            return; // Exit early to prevent booking if invalid choice
                    }

                    System.out.print("Enter Patient Mobile Number: ");
                    String mobileNumber = scanner.nextLine().trim();
                    System.out.print("Enter Physiotherapist ID: ");
                    String physioId = scanner.nextLine().trim();
                    System.out.print("Enter Treatment ID: ");
                    String treatmentId = scanner.nextLine().trim();

                    System.out.println(system.bookTreatment(mobileNumber, physioId, treatmentId,expertise.trim()));
                    break;
                case 4:
                    System.out.print("Enter Physiotherapist ID: ");
                    String physioCancelId = scanner.nextLine();
                    System.out.print("Enter Treatment ID to cancel: ");
                    String treatmentCancelId = scanner.nextLine();
                    System.out.println(system.cancelBooking(physioCancelId.trim(), treatmentCancelId.trim()));
                    break;

                case 5:
                    System.out.print("Enter Physiotherapist ID: ");
                    String physioAttendId = scanner.nextLine();
                    System.out.print("Enter Treatment ID to mark as attended: ");
                    String treatmentAttendId = scanner.nextLine();
                    System.out.println(system.attendTreatment(physioAttendId, treatmentAttendId));
                    break;

                case 6:
                    system.printReport();
                    break;

                case 7:
                    System.out.println("Thank you and Please Visit Again ................ :)");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}