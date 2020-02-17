package ca.uqtr.patient.controller;

import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.dto.UserRequestDto;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.service.patient.PatientService;
import ca.uqtr.patient.service.professional.ProfessionalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    private PatientService patientService;
    private ProfessionalService professionalService;

    @Autowired
    public PatientController(PatientService patientService, ProfessionalService professionalService) {
        this.patientService = patientService;
        this.professionalService = professionalService;
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patient){
        System.out.println(patient.toString());
        return new ResponseEntity<>(patientService.addPatient(patient), HttpStatus.CREATED);
    }

    @PostMapping(value = "/create/professional")
    @ResponseBody
    public void addProfessional(@RequestBody UserRequestDto userRequestDto){
        System.out.println(userRequestDto.toString());
        professionalService.createProfessional(userRequestDto);
    }

    @PostMapping(value = "/id")
    @ResponseBody
    public PatientDto getPatient(@RequestBody PatientDto patient){
        return patientService.getPatientById(patient);
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Iterable<Patient> getPatients(){
        return patientService.getPatients();
    }

    @GetMapping(value = "/all/professional")
    @ResponseBody
    public Iterable<Patient> getPatientsByProfessional(@RequestBody PatientDto patient){
        return patientService.getPatientsByProfessional(patient);
    }

    @PostMapping(value = "/patientByName")
    @ResponseBody
    public Patient getPatientByFirstNameAndLastName(@RequestBody PatientDto patient) {
        return patientService.getPatientByFirstNameAndLastName(patient);
    }

    @PostMapping(value = "/patientByAge")
    @ResponseBody
    public List<Patient> getPatientsByAge(@RequestBody PatientDto patient) {
        return patientService.getPatientsByAge(patient);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Patient updatePatient(@RequestBody PatientDto patient){
        return patientService.updatePatient(patient);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deletePatient(@RequestBody PatientDto patient){
        patientService.deleteById(patient);
    }

}
