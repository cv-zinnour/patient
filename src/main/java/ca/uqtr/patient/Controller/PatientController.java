package ca.uqtr.patient.Controller;

import ca.uqtr.patient.DTO.PatientDTO;
import ca.uqtr.patient.Entity.Patient;
import ca.uqtr.patient.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    //--------------------------------------------------GET
    @GetMapping(value = "/{id}")
    @ResponseBody
    public void getPatient(@PathVariable UUID id){

    }

    @GetMapping(value = "/all")
    @ResponseBody
    public void getPatients(){

    }

    @GetMapping(value = "/expert/{id}")
    @ResponseBody
    public void getPatientByExpertId(@PathVariable UUID id){

    }

    public void getPatientByName(){

    }

    public void getPatientsByAge(@PathVariable Integer age){

    }

    public void getMedicalFileByPatientId(@PathVariable UUID id){

    }

    //--------------------------------------------------POST

    @PostMapping
    @ResponseBody
    public PatientDTO newPatient(@PathVariable PatientDTO patient){
        return new PatientDTO();
    }


    //--------------------------------------------------PUT

    public void updatePatient(@PathVariable PatientDTO patient){

    }




    //--------------------------------------------------DELETE

    public void deletePatient(@PathVariable UUID id){

    }

}
