package ca.uqtr.patient.service.medicalFile;

import ca.uqtr.patient.dto.MedicalFileDto;
import ca.uqtr.patient.dto.PatientDto;
import ca.uqtr.patient.entity.MedicalFile;
import ca.uqtr.patient.repository.medicalFile.MedicalFileRepository;
import ca.uqtr.patient.repository.patient.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalFileServiceImpl implements MedicalFileService {

    private MedicalFileRepository medicalFileRepository;
    private ModelMapper modelMapper;

    @Autowired
    public MedicalFileServiceImpl(MedicalFileRepository medicalFileRepository, ModelMapper modelMapper) {
        this.medicalFileRepository = medicalFileRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public MedicalFile getMedicalFileByPatient(PatientDto patient) {
        MedicalFileDto medicalFileDto = new MedicalFileDto();
        medicalFileDto.setPatient(patient.getId().toString());
        System.out.println(medicalFileDto.getPatient());
        return medicalFileRepository.getMedicalFileByPatient(medicalFileDto.getPatient());
    }

    @Override
    public MedicalFile newMedicalFile(MedicalFileDto medicalFile) {
        return medicalFileRepository.save(medicalFile.medicalFile2Dto(modelMapper));
    }

    @Override
    public MedicalFile updateMedicalFile(MedicalFileDto medicalFile) {
        return medicalFileRepository.save(medicalFile.medicalFile2Dto(modelMapper));
    }

    @Override
    public void deleteMedicalFileById(MedicalFileDto medicalFile) {
        medicalFileRepository.deleteByPatient(medicalFile.medicalFile2Dto(modelMapper).getPatient());
    }

}
