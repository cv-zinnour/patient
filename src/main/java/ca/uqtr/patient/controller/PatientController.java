package ca.uqtr.patient.controller;

import ca.uqtr.patient.dto.*;
import ca.uqtr.patient.dto.medicalfile.AntecedentsDto;
import ca.uqtr.patient.dto.medicalfile.SocioDemographicVariablesDto;
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

    @GetMapping(value = "/id")
    @ResponseBody
    public Response getPatient(@RequestParam String patientId){
        return patientService.getPatient(patientId);
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Response getPatients(){
        return patientService.getPatients();
    }

    @GetMapping(value = "/all/professional")
    @ResponseBody
    public Response getPatientsByProfessional(HttpServletRequest request)  {
        String token = request.getHeader("Authorization").replace("bearer ","");
        return patientService.getPatientsByProfessional(JwtTokenUtil.getId(token));
    }

    @GetMapping(value = "/socio")
    @ResponseBody
    public Response getPatientSocioDemographicVariables(@RequestParam String patientId) throws IOException {
        return patientService.getPatientSocioDemographicVariables(patientId);
    }

    @PostMapping(value = "/socio")
    @ResponseBody
    public Response addSocioDemographicVariables(@RequestParam String patientId, @RequestBody Request request) throws JsonProcessingException {
        SocioDemographicVariablesDto socioDemographicVariables = mapper.convertValue(request.getObject(), SocioDemographicVariablesDto.class);
        return patientService.addSocioDemographicVariables(patientId, mapper.writeValueAsString(socioDemographicVariables));
    }

    @GetMapping(value = "/antecedents")
    @ResponseBody
    public Response getPatientAntecedents(@RequestParam String patientId) {
        return patientService.getPatientAntecedents(patientId);
    }

    @PostMapping(value = "/antecedents")
    @ResponseBody
    public Response addAntecedents(@RequestParam String patientId, @RequestBody Request request) throws JsonProcessingException {
        System.out.println(request.getObject());

        return patientService.addAntecedents(patientId, mapper.writeValueAsString(request.getObject()));
    }

    @GetMapping(value = "/clinicalexamination")
    @ResponseBody
    public Response getPatientClinicalExamination(@RequestParam String patientId) {
        return patientService.getPatientClinicalExaminationList(patientId);
    }

    @PostMapping(value = "/clinicalexamination")
    @ResponseBody
    public Response addClinicalExamination(@RequestParam String patientId, @RequestBody Request request) {
        ClinicalExaminationDto clinicalExaminationDto = mapper.convertValue(request.getObject(), ClinicalExaminationDto.class);
        return patientService.addClinicalExamination(patientId, clinicalExaminationDto);
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public Response updatePatient(@RequestBody Request request){
        PatientDto patient = mapper.convertValue(request.getObject(), PatientDto.class);
        return patientService.updatePatient(patient);
    }

    @GetMapping(value = "/lipidprofile")
    @ResponseBody
    public Response getLipidProfile(@RequestParam String patientId) {
        return patientService.getPatientLipidProfile(patientId);
    }

    @PostMapping(value = "/lipidprofile")
    @ResponseBody
    public Response addLipidProfile(@RequestParam String patientId, @RequestBody LipidProfileDto lipidProfileDto) {
        return patientService.addLipidProfile(patientId, lipidProfileDto);
    }

}
