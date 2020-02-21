package ca.uqtr.patient.dto;


import lombok.Data;

@Data
public class Error {

    private int id;
    private String message;

    public Error(int id, String message) {
        this.id = id;
        this.message = message;
    }
}
