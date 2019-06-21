package group.flowbird.mediationservice.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestClientException;

public class RestClient {

    private OAuth2RestTemplate restTemplate;

    public RestClient(OAuth2RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<String> performRequest( HttpEntity<T> requestEntity,
                                                  HttpMethod requestType,
                                                  String endpoint) throws RestClientException {

        return restTemplate.exchange(endpoint, requestType, requestEntity, String.class);
    }
}
