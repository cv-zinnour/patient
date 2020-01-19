package ca.uqtr.patient;


import ca.uqtr.patient.controller.PatientController;
import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.entity.Expert;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.VO.*;
import ca.uqtr.patient.repository.medicalFile.MedicalFileRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientControllerIntegrationTests {

    @Autowired
    private PatientController patientController;
    @Autowired
    private MedicalFileRepository medicalFileRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void newPatient() throws ParseException {

        Logger.getGlobal().info("Start newPatient test");

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        Date date = sdf.parse("06/24/2017");

        Address address = new Address("Jean talon", 10, "TR", "XYZ 123", "QC");

        Patient p = new Patient("samy1", "", "luck1", date, true);

        List<Profession> professions = new ArrayList<>();
        Profession p1 = new Profession("profession 1", true);
        p1.setPatient(p);
        professions.add(p1);
        //p.setProfession(professions);

        List<Expert> experts = new ArrayList<>();
        Expert e1 = new Expert(UUID.randomUUID(), "fn1", "ln1");
        e1.setPatient(p);
        experts.add(e1);
        //p.setExpert(experts);

        Contact contact = new Contact((long) 888000111, "test@me.ca", address);
        contact.setPatient(p);
        //p.setContact(contact);

        MedicalFile medicalFile = new MedicalFile("22222");

        List<Allergy> allergies = new ArrayList<>();
        allergies.add(new Allergy("Nuts"));
        allergies.add(new Allergy("Penicillin"));
        allergies.get(0).setMedicalFile(medicalFile);
        allergies.get(1).setMedicalFile(medicalFile);
        medicalFile.setAllergies(allergies);

        List<BloodPressure> bloodPressureList = new ArrayList<>();
        bloodPressureList.add(new BloodPressure(10.0, date));
        bloodPressureList.get(0).setMedicalFile(medicalFile);
        medicalFile.setBloodPressure(bloodPressureList);

        List<Condition> conditions = new ArrayList<>();
        conditions.add(new Condition("Gluacoma"));
        conditions.get(0).setMedicalFile(medicalFile);
        medicalFile.setCondition(conditions);

        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Running", date, 40));
        exercises.get(0).setMedicalFile(medicalFile);
        medicalFile.setExercises(exercises);

        List<MedicalDevice> medicalDevices = new ArrayList<>();
        medicalDevices.add(new MedicalDevice("Fitbit zip"));
        medicalDevices.get(0).setMedicalFile(medicalFile);
        medicalFile.setMedicalDevices(medicalDevices);

        List<Medicament> medicaments = new ArrayList<>();
        medicaments.add(new Medicament("Arava"));
        medicaments.add(new Medicament("Nystatin"));
        medicaments.get(0).setMedicalFile(medicalFile);
        medicaments.get(1).setMedicalFile(medicalFile);
        medicalFile.setMedicaments(medicaments);



       /* p1.setPatient(p);
        e1.setPatient(p);
        e2.setPatient(p);professions.add(p1);
        experts.add(e1); experts.add(e2);
        p.setProfession(professions);
        p.setExpert(experts);*/


        PatientDto pdto = modelMapper.map(p, PatientDto.class);

        ResponseEntity<Patient> patient = patientController.newPatient(pdto);
        System.out.println("------------"+ p.getId());

        medicalFile.setPatient(p.getId().toString());

        medicalFileRepository.save(medicalFile);
        Assert.assertEquals(HttpStatus.OK, patient.getStatusCode());
        Assert.assertTrue(patient.hasBody());
        Assert.assertNotNull(patient.getBody());
        Logger.getGlobal().info(String.valueOf(patient.toString()));
        Logger.getGlobal().info("End newPatient test");
    }


    @Test
    public void getPatient() {
        UUID id = UUID.fromString("e3ec29b7-5edc-4a32-81f1-5ab31a04c988");
        PatientDto pdto = new PatientDto();
        pdto.setId(id);
        Optional<Patient> p = patientController.getPatient(pdto);

        System.out.println("------------"+p.toString());

    }

}
