package ca.uqtr.patient.Exception;

public class PatientNotFoundException extends RuntimeException {

    PatientNotFoundException(String id) {
        super("Could not find patient " + id);
    }
}
