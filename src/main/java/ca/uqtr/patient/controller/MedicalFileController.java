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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient/medicalfile")
public class MedicalFileController {

    private MedicalFileService medicalFileService;

    @Autowired
    public MedicalFileController(MedicalFileService medicalFileService) {
        this.medicalFileService = medicalFileService;
    }

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MedicalFile getMedicalFileByPatientId(@RequestBody PatientDto patient){
        return medicalFileService.getMedicalFileByPatient(patient);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<MedicalFile> newMedicalFile(@RequestBody MedicalFileDto medicalFile){
        return new ResponseEntity<>(medicalFileService.newMedicalFile(medicalFile), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MedicalFile updateMedicalFile(@RequestBody MedicalFileDto medicalFile){
        return medicalFileService.updateMedicalFile(medicalFile);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteMedicalFile(@RequestBody MedicalFileDto medicalFile){
        medicalFileService.deleteMedicalFileById(medicalFile);
    }


}
