package ca.uqtr.patient.dto;


import lombok.Data;

@Data
public class Request {

    private Object object;

    public Request() {
    }

    public Request(Object object) {
        this.object = object;
    }
}
