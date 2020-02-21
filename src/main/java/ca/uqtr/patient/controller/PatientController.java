package ca.uqtr.patient.controller;

import ca.uqtr.patient.dto.*;
import ca.uqtr.patient.dto.medicalfile.clinical_examination.ClinicalExaminationDto;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.service.patient.PatientService;
import ca.uqtr.patient.service.professional.ProfessionalService;
import ca.uqtr.patient.utils.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class PatientController {

    private PatientService patientService;
    private ProfessionalService professionalService;
    private ObjectMapper mapper;

    @Autowired
    public PatientController(PatientService patientService, ProfessionalService professionalService, ObjectMapper mapper) {
        this.patientService = patientService;
        this.professionalService = professionalService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public Response addPatient(@RequestBody Request request, HttpServletRequest HttpRequest) throws IOException {
        String token = HttpRequest.getHeader("Authorization").replace("bearer ","");
        PatientDto patient = mapper.convertValue(request.getObject(), PatientDto.class);
        return patientService.addPatient(patient , JwtTokenUtil.getId(token));
    }

    @PostMapping(value = "/id")
    @ResponseBody
    public Response getPatient(@RequestBody Request request){
        PatientDto patient = mapper.convertValue(request.getObject(), PatientDto.class);
        return patientService.getPatientById(patient);
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Response getPatients(){
        return patientService.getPatients();
    }

    @GetMapping(value = "/patients/professional")
    @ResponseBody
    public Response getPatientsByProfessional(HttpServletRequest request)  {
        String token = request.getHeader("Authorization").replace("bearer ","");
        return patientService.getPatientsByProfessional(JwtTokenUtil.getId(token));
    }

    @GetMapping(value = "/patient/socio")
    @ResponseBody
    public Response getPatientSocioDemographicVariables(@RequestParam String patientId) throws JsonProcessingException {
        return patientService.getPatientSocioDemographicVariables(patientId);
    }

    @PostMapping(value = "/patient/socio")
    @ResponseBody
    public Response addSocioDemographicVariables(@RequestParam String patientId, @RequestBody Request request) throws JsonProcessingException {
        String socioDemographicVariables = mapper.convertValue(request.getObject(), String.class);
        return patientService.addSocioDemographicVariables(patientId, socioDemographicVariables);
    }

    @PostMapping(value = "/patient/antecedents")
    @ResponseBody
    public Response addAntecedents(@RequestParam String patientId, @RequestBody String antecedents) throws JsonProcessingException {
        return patientService.addAntecedents(patientId, antecedents);
    }

    @PostMapping(value = "/patient/clinicalexamination")
    @ResponseBody
    public Response addClinicalExamination(@RequestParam String patientId, @RequestBody ClinicalExaminationDto clinicalExaminationDto) {
        return patientService.addClinicalExamination(patientId, clinicalExaminationDto);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response updatePatient(@RequestBody PatientDto patient){
        return patientService.updatePatient(patient);
    }

}
