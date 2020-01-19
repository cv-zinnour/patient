package ca.uqtr.patient.exception;

public class PatientNotFoundException extends RuntimeException {

    PatientNotFoundException(String id) {
        super("Could not find patient " + id);
    }
}
