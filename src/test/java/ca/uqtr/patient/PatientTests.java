package ca.uqtr.patient;


import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.dto.Request;
import ca.uqtr.patient.dto.medicalfile.SocioDemographicVariablesDto;
import ca.uqtr.patient.dto.medicalfile.clinical_examination.ClinicalExaminationDto;
import ca.uqtr.patient.dto.medicalfile.clinical_examination.SmokingDto;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.service.patient.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientTests {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    private PatientService patientService;
    @Autowired
    private ModelMapper modelMapper;


    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("addPatient"));
    }

    @Test
    public void addPatient() throws Exception {
        Patient patient = new Patient("99", "zinnour");
        String date = "2015-04-12";
        java.sql.Date birthday = java.sql.Date.valueOf(date);
        patient.setBirthday(birthday);

        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
        Request request = new Request(patientDto);
        System.out.println(patientService.addPatient(patientDto, "938d4606-6f4f-4c9d-8a8a-9a06864339c1"));

        /*this.mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(request)))
                .andExpect(status().isCreated());*/
    }

    @Test
    public void getPatientsByProfessional() throws Exception {

        this.mockMvc.perform(get("/patients/professional"));

    }

    @Test
    public void addClinicalExamination() throws Exception {

        PatientDto patientId = (PatientDto) patientService.getPatients().getObject();
        ClinicalExaminationDto clinicalExaminationDto = new ClinicalExaminationDto();
        clinicalExaminationDto.setSmoking(new SmokingDto("active", 6));

        patientService.updateClinicalExamination(patientId.getId().toString(), clinicalExaminationDto);

    }

    @Test
    public void addSocioDemographicVariables() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        PatientDto patientId = (PatientDto) patientService.getPatients().getObject();
        SocioDemographicVariablesDto socioDemographicVariablesDto = new SocioDemographicVariablesDto();
        socioDemographicVariablesDto.setAge(66);
        //socioDemographicVariablesDto.setFamilyIncome(2000);
        socioDemographicVariablesDto.setJobStatus("retired");

        String s = mapper.writeValueAsString(socioDemographicVariablesDto);
        System.out.println(s);
        patientService.addSocioDemographicVariables(patientId.getId().toString(), s);

    }


    @Test
    public void getSocioDemographicVariables() throws Exception {

        /*PatientDto patientId = (PatientDto) patientService.getPatients().getObject();
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patientId.getId().toString());
        String socio = (String) patientService.getPatientSocioDemographicVariables(patientDto.getId().toString()).getObject();
        SocioDemographicVariablesDto socioDemographicVariablesDto = modelMapper.map(socio, SocioDemographicVariablesDto.class);
        System.out.println(socioDemographicVariablesDto);*/
    }

}
