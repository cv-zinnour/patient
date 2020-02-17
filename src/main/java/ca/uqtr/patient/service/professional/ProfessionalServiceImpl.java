package ca.uqtr.patient.service.professional;

import ca.uqtr.patient.dto.UserRequestDto;
import ca.uqtr.patient.entity.Professional;
import ca.uqtr.patient.repository.ProfessionalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {

    final
    ProfessionalRepository professionalRepository;

    public ProfessionalServiceImpl(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public void createProfessional(UserRequestDto userRequestDto) {
        Professional professional = new Professional(
                UUID.fromString(userRequestDto.getId()),
                userRequestDto.getFirstName(),
                userRequestDto.getLastName(),
                true);

        professionalRepository.save(professional);
    }
}
