import com.boostPhysioClinic.model.Patient;
import com.boostPhysioClinic.services.BookingSystemService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BookingSystemServiceTest {
    private BookingSystemService bookingSystem;

    @Before
    public void setUp() {
        bookingSystem = new BookingSystemService();
    }

    @Test
    public void testAddPatient() {
        Patient patient = new Patient("John Doe", "123 Main St", "9876543210");
        String result = bookingSystem.addPatient(patient);
        assertEquals("Registered Successfully", result);

        // Adding same patient again should return already registered message
        result = bookingSystem.addPatient(patient);
        assertEquals("Patient already registered", result);
    }

    @Test
    public void testRemovePatient() {
        Patient patient = new Patient("John Doe", "123 Main St", "9876543210");
        bookingSystem.addPatient(patient);

        // Remove patient
        String result = bookingSystem.removePatient("9876543210");
        assertEquals("Patient removed successfully", result);

        // Removing again should return "not found"
        result = bookingSystem.removePatient("9876543210");
        assertEquals("Patient not found", result);
    }

    @Test
    public void testBookTreatment_Success() {
        Patient patient = new Patient("John Doe", "123 Main St", "9876543210");
        bookingSystem.addPatient(patient);

        String result = bookingSystem.bookTreatment("9876543210", "1", "1", null);
        assertEquals("Treatment Booked Successfully", result);
    }

    @Test
    public void testBookTreatment_Failure_NoPatient() {
        String result = bookingSystem.bookTreatment("9999999999", "1", "1", null);
        assertEquals("Patient not found", result);
    }

    @Test
    public void testBookTreatment_Failure_NoPhysiotherapist() {
        Patient patient = new Patient("John Doe", "123 Main St", "9876543210");
        bookingSystem.addPatient(patient);

        String result = bookingSystem.bookTreatment("9876543210", "99", "1", null);
        assertEquals("Physiotherapist not found", result);
    }

    @Test
    public void testBookTreatment_Failure_AlreadyBooked() {
        Patient patient = new Patient("John Doe", "123 Main St", "9876543210");
        bookingSystem.addPatient(patient);

        // First booking should succeed
        String result = bookingSystem.bookTreatment("9876543210", "1", "1", null);
        assertEquals("Treatment Booked Successfully", result);

        // Second booking should fail
        result = bookingSystem.bookTreatment("9876543210", "1", "1", null);
        assertEquals("Treatment already booked", result);
    }

    @Test
    public void testCancelBooking_Success() {
        Patient patient = new Patient("John Doe", "123 Main St", "9876543210");
        bookingSystem.addPatient(patient);

        bookingSystem.bookTreatment("9876543210", "1", "1", null);
        String result = bookingSystem.cancelBooking("1", "1");
        assertEquals("Treatment Canceled Successfully", result);
    }

    @Test
    public void testCancelBooking_Failure_NoBooking() {
        String result = bookingSystem.cancelBooking("1", "1");
        assertEquals("Please Book Treatment", result);
    }

    @Test
    public void testAttendTreatment_Success() {
        Patient patient = new Patient("John Doe", "123 Main St", "9876543210");
        bookingSystem.addPatient(patient);

        bookingSystem.bookTreatment("9876543210", "1", "1", null);
        String result = bookingSystem.attendTreatment("1", "1");
        assertEquals("Attended for the treatment", result);
    }

    @Test
    public void testAttendTreatment_Failure_NoBooking() {
        String result = bookingSystem.attendTreatment("1", "1");
        assertEquals("Please Book Treatment", result);
    }

    @Test
    public void testShowPhysiotherapists() {
        assertDoesNotThrow(() -> bookingSystem.showPhysiotherapists());
    }

    @Test
    public void testShowPhysiotherapists_ByExpertise() {
        assertDoesNotThrow(() -> bookingSystem.showPhysiotherapists("Physiotherapy"));
    }

    @Test
    public void testShowPhysiotherapists_Failure_InvalidExpertise() {
        assertDoesNotThrow(() -> bookingSystem.showPhysiotherapists("UnknownExpertise"));
    }
}

