package ca.uqtr.patient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDto {
    private int error_id;
    private String error_description;

    public ErrorDto(int error_id, String error_description) {
        this.error_id = error_id;
        this.error_description = error_description;
    }
}
