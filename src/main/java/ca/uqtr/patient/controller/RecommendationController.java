package ca.uqtr.patient.controller;

import ca.uqtr.patient.dto.RecommendationDto;
import ca.uqtr.patient.dto.Request;
import ca.uqtr.patient.dto.Response;
import ca.uqtr.patient.service.recommendation.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class RecommendationController {

    private ModelMapper modelMapper;
    private RecommendationService recommendationService;

    public RecommendationController(ModelMapper modelMapper, RecommendationService recommendationService) {
        this.modelMapper = modelMapper;
        this.recommendationService = recommendationService;
    }

    @PostMapping(value = "/recommendation")
    @ResponseBody
    public Response addRecommendation(@RequestBody Request request)  {
        RecommendationDto recommendationDto = modelMapper.map(request.getObject(), RecommendationDto.class);
        return recommendationService.addRecommendation(recommendationDto);
    }

    @PostMapping(value = "/recommendation")
    @ResponseBody
    public Response getRecommendationsByPatient(@RequestParam String patientId)  {
        return recommendationService.getRecommendationsByPatient(patientId);
    }

}
