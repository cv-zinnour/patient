package ca.uqtr.patient;


import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.repository.medicalFile.MedicalFileRepository;
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
public class PatientControllerIntegrationTests {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    private MedicalFileRepository medicalFileRepository;
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
        Patient patient = new Patient("lahcene", "zinnour");
        String date = "2015-04-12";
        java.sql.Date birthday = java.sql.Date.valueOf(date);
        patient.setFileNumber("lahcene", "zinnour", birthday);

        ObjectMapper mapper = new ObjectMapper();
        String patientDto = mapper.writeValueAsString(patient);
        System.out.println(patientDto);
        this.mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON)
                .content(patientDto))
                .andExpect(status().isCreated());
    }

    @Test
    public void getPatient() {


    }

}
