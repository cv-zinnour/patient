package ca.uqtr.patient.controller;

import ca.uqtr.patient.dto.MedicalFileDto;
import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.service.medicalFile.MedicalFileService;
import ca.uqtr.patient.service.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Patient> newPatient(@RequestBody PatientDto patient){
        return new ResponseEntity<>(patientService.newPatient(patient), HttpStatus.OK);
    }

    @PostMapping(value = "/patientById")
    @ResponseBody
    public Optional<Patient> getPatient(@RequestBody PatientDto patient){

        return patientService.getPatientById(patient);
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Iterable<Patient> getPatients(){
        return patientService.getPatients();
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
