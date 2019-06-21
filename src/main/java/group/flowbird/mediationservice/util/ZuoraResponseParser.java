package group.flowbird.mediationservice.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@Slf4j
public class ZuoraResponseParser {

    @Getter
    private boolean isExpectedResponseCode = false;

    private Optional<ResponseEntity<String>> response = Optional.empty();

    public ZuoraResponseParser parseResponse(ResponseEntity<String> response, HttpStatus expectedResponseCode) {

        this.response = Optional.of(response);
        return parseResponse(expectedResponseCode);
    }

    private ZuoraResponseParser parseResponse(HttpStatus expectedResponseCode) {

        isExpectedResponseCode = response.map(ResponseEntity::getStatusCode)
                .orElse(HttpStatus.SERVICE_UNAVAILABLE)
                .equals(expectedResponseCode);

        return this;
    }

    public <T> T getResponseBody(Class<T> classType) throws Exception {

        return mapObjectFromString(

                response.map(ResponseEntity::getBody)
                        .orElse(""),
                classType
        );
    }

    private <T> T mapObjectFromString(String responseString, Class<T> classType) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if(classType.equals(String.class)) {
            return (T) responseString;
        }
        return (T) objectMapper.readValue(responseString, classType);
    }
}
