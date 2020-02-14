package ca.uqtr.patient;

import ca.uqtr.patient.controller.MedicalFileController;
import ca.uqtr.patient.dto.MedicalFileDto;
import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.entity.MedicalFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicalFileControllerTests {

    @Autowired
    private MedicalFileController medicalFileController;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void getMedicalFileByPatientId() {
        UUID id = UUID.fromString("e3ec29b7-5edc-4a32-81f1-5ab31a04c988");
        PatientDto pdto = new PatientDto();
        pdto.setId(id.toString());

        System.out.println("------------"+pdto.toString());
        MedicalFile mf = medicalFileController.getMedicalFileByPatientId(pdto);

        System.out.println("------------"+mf.toString());

    }


    @Test
    public void newMedicalFile() {
        UUID id = UUID.fromString("e3ec29b7-5edc-4a32-81f1-5ab31a04c988");
        MedicalFileDto mfdto = new MedicalFileDto();
        mfdto.setPatient(id.toString());

        System.out.println("------------"+mfdto.toString());
        ResponseEntity<MedicalFile> mf = medicalFileController.newMedicalFile(mfdto);

        System.out.println("------------"+mf.toString());

    }

}
