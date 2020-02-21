package ca.uqtr.patient;


import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.dto.Request;
import ca.uqtr.patient.dto.medicalfile.AntecedentsDto;
import ca.uqtr.patient.dto.medicalfile.SocioDemographicVariablesDto;
import ca.uqtr.patient.dto.medicalfile.clinical_examination.ClinicalExaminationDto;
import ca.uqtr.patient.dto.medicalfile.clinical_examination.SmokingDto;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.repository.medicalFile.MedicalFileRepository;
import ca.uqtr.patient.service.patient.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
        Patient patient = new Patient("sade9", "zinnour");
        String date = "2015-04-12";
        java.sql.Date birthday = java.sql.Date.valueOf(date);
        patient.setBirthday(birthday);


        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
        Request request = new Request(patientDto);
        ObjectMapper om = new ObjectMapper();

        this.mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getPatientsByProfessional() throws Exception {

        this.mockMvc.perform(get("/patients/professional"));

    }

    @Test
    public void addClinicalExamination() throws Exception {

        String patientId = patientService.getPatients().iterator().next().getId().toString();
        ClinicalExaminationDto clinicalExaminationDto = new ClinicalExaminationDto();
        clinicalExaminationDto.setSmoking(new SmokingDto("active", 6));

        patientService.addClinicalExamination(patientId, clinicalExaminationDto);

    }

    @Test
    public void addSocioDemographicVariables() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String patientId = patientService.getPatients().iterator().next().getId().toString();
        SocioDemographicVariablesDto socioDemographicVariablesDto = new SocioDemographicVariablesDto();
        socioDemographicVariablesDto.setAge(66);
        socioDemographicVariablesDto.setFamilyIncome(200000);
        socioDemographicVariablesDto.setJobStatus("retired");

        String s = mapper.writeValueAsString(socioDemographicVariablesDto);
        System.out.println(s);
        patientService.addSocioDemographicVariables(patientId, s);

    }

    @Test
    public void addAntecedents() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        String patientId = patientService.getPatients().iterator().next().getId().toString();
        AntecedentsDto antecedentsDto = new AntecedentsDto();
        antecedentsDto.setType("test");
        antecedentsDto.setYear(2015);

        String s = mapper.writeValueAsString(antecedentsDto);
        System.out.println(s);
        patientService.addAntecedents(patientId, s);

    }

    @Test
    public void getSocioDemographicVariables() throws Exception {

        String patientId = patientService.getPatients().iterator().next().getId().toString();
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patientId);

        SocioDemographicVariablesDto socioDemographicVariablesDto = patientService.getSocioDemographicVariables(patientDto);
        System.out.println(socioDemographicVariablesDto);
    }

}
