package ca.uqtr.patient.service.recommendation;

import ca.uqtr.patient.dto.RecommendationDto;
import ca.uqtr.patient.dto.Response;
import org.springframework.web.bind.annotation.RequestParam;

public interface RecommendationService {
    Response addRecommendation(RecommendationDto recommendationDto);

    Response getLastRecommendationByPatient(String patientId);

    Response updateRecommendationByPatient(String patientId);

    Response getRecommendationsByPatient(String patientId);

    }
