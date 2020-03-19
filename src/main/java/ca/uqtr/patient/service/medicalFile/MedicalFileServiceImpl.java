package ca.uqtr.patient.service.medicalFile;

import ca.uqtr.patient.dto.MedicalFileDto;
import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.repository.medicalFile.MedicalFileRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.DigestMethod;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA3_256;


@Service
public class MedicalFileServiceImpl implements MedicalFileService {

    private MedicalFileRepository medicalFileRepository;
    private ModelMapper modelMapper;
    @Value("${sha3-256.salt}")
    private String SALT;
    public static final String SHA3_256 = "SHA3-256";

    @Autowired
    public MedicalFileServiceImpl(MedicalFileRepository medicalFileRepository, ModelMapper modelMapper) {
        this.medicalFileRepository = medicalFileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MedicalFile getMedicalFileByPatient(PatientDto patient) {
        return medicalFileRepository.getMedicalFileByPatient(new DigestUtils(SHA3_256).digestAsHex(patient.getId().toString().concat(SALT)));
    }

    @Override
    public MedicalFile newMedicalFile(MedicalFileDto medicalFile) {
        String patientId = medicalFile.getPatient();
        String patientIdSHA = new DigestUtils(SHA3_256).digestAsHex(patientId.concat(SALT));
        medicalFile.setPatient(patientIdSHA);
        return medicalFileRepository.save(medicalFile.dtoToObj(modelMapper));
    }

    @Override
    public MedicalFile updateMedicalFile(MedicalFileDto medicalFile) {
        return medicalFileRepository.save(medicalFile.dtoToObj(modelMapper));
    }

    @Override
    public void deleteMedicalFileById(MedicalFileDto medicalFile) {
        medicalFileRepository.deleteByPatient(medicalFile.dtoToObj(modelMapper).getPatient());
    }

}
