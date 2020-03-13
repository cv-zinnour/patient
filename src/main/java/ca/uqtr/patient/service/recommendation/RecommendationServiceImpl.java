package ca.uqtr.patient.service.recommendation;

import ca.uqtr.patient.dto.Error;
import ca.uqtr.patient.dto.QuestionnaireDto;
import ca.uqtr.patient.dto.RecommendationDto;
import ca.uqtr.patient.dto.Response;
import ca.uqtr.patient.entity.Patient;
import ca.uqtr.patient.entity.Questionnaire;
import ca.uqtr.patient.entity.Recommendation;
import ca.uqtr.patient.repository.patient.PatientRepository;
import javassist.bytecode.stackmap.TypeData;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );

    private PatientRepository patientRepository;
    private MessageSource messageSource;
    private final ModelMapper modelMapper;

    public RecommendationServiceImpl(PatientRepository patientRepository, MessageSource messageSource, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.messageSource = messageSource;
        this.modelMapper = modelMapper;
    }


    @Override
    public Response addRecommendation(RecommendationDto recommendationDto) {
        Patient patient = patientRepository.getPatientById(UUID.fromString(recommendationDto.getPatientId()));
        if (patient == null)
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.patient.exist.id", null, Locale.US)),
                            messageSource.getMessage("error.patient.exist.message", null, Locale.US)));

        try {
            Recommendation recommendation = recommendationDto.dtoToObj(modelMapper);
            List<Recommendation> recommendations = patient.getRecommendations();
            recommendations.add(recommendation);
            patientRepository.save(patient);
            return new Response(recommendationDto, null);
        } catch (Exception e){
            LOGGER.log( Level.WARNING, e.getMessage());
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.patient.recommendation.id", null, Locale.US)),
                            messageSource.getMessage("error.patient.recommendation.message", null, Locale.US)));
        }
    }

    @Override
    public Response getRecommendationsByPatient(String patientId){
        Patient patient = patientRepository.getPatientById(UUID.fromString(patientId));
        if (patient == null)
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.patient.exist.id", null, Locale.US)),
                            messageSource.getMessage("error.patient.exist.message", null, Locale.US)));
        try {
            List<Recommendation> recommendations = patient.getRecommendations();
            Type recommendationType = new TypeToken<List<RecommendationDto>>() {}.getType();
            List<RecommendationDto> recommendationDtoList = modelMapper.map(recommendations, recommendationType);

            return new Response(recommendationDtoList, null);
        } catch (Exception e){
            LOGGER.log( Level.WARNING, e.getMessage());
            return new Response(null,
                    new Error(Integer.parseInt(messageSource.getMessage("error.patient.recommendation.id", null, Locale.US)),
                            messageSource.getMessage("error.patient.recommendation.message", null, Locale.US)));
        }

    }


}
