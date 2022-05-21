package andreistefanwork.backend.controllers;

import andreistefanwork.backend.dtos.AgePrediction;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/age/prediction")
@RequiredArgsConstructor
public class AgePredictionController {
    private final static String API_ENDPOINT = "https://api.agify.io";

    private final RestTemplate restTemplate;

    /**
     * Tries to predict the age for the provided name.
     *
     * If name is empty, an empty prediction is returned.
     *
     * @param name used for age prediction
     * @return age prediction for given name
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public AgePrediction predictAge(@RequestParam(required = false) String name) {
        if (StringUtils.isEmpty(name)) {
            return AgePrediction.empty();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(buildAgePredictionForNameURL(name),
                HttpMethod.GET, entity, AgePrediction.class).getBody();
    }

    private String buildAgePredictionForNameURL(String name) {
        return UriComponentsBuilder
                .fromHttpUrl(API_ENDPOINT)
                .queryParam("name", name)
                .toUriString();
    }
}
