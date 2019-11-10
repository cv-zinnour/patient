package ca.uqtr.patient.Controller;

import ca.uqtr.patient.Entity.Patient;
import ca.uqtr.patient.Repository.PatientRepository;
import ca.uqtr.patient.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {

    PatientService patientService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }
    //--------------------------------------------------GET
    @GetMapping(value = "/{id}")
    @ResponseBody
    public Optional<Patient> getPatient(@PathVariable UUID id){
        return patientRepository.findById(id);
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Iterable<Patient> getPatients(){
        return patientRepository.findAll();
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
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Patient> newPatient(@RequestBody Patient patient){
        return new ResponseEntity<>(patientService.newPatient(patient), HttpStatus.OK);
    }


    //--------------------------------------------------PUT

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Patient updatePatient(@PathVariable Patient patient){
        return patientService.updatePatient(patient);
    }




    //--------------------------------------------------DELETE
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deletePatient(@PathVariable UUID id){
        patientService.deleteById(id);
    }

}
