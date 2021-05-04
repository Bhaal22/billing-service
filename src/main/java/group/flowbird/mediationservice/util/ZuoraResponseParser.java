package group.flowbird.mediationservice.util;

import group.flowbird.mediationservice.dto.BaseResponseDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static group.flowbird.mediationservice.util.RestUtils.mapObjectFromString;

@Slf4j
public class ZuoraResponseParser {

    @Getter
    private boolean isSuccessfulRequest = false;

    private Optional<ResponseEntity<String>> response = Optional.empty();

    public ZuoraResponseParser parseResponse(ResponseEntity<String> response, HttpStatus expectedResponseCode) throws Exception {

        this.response = Optional.of(response);
        return parseResponse(expectedResponseCode);
    }

    private ZuoraResponseParser parseResponse(HttpStatus expectedResponseCode) throws Exception {

        isSuccessfulRequest = response.map(ResponseEntity::getStatusCode)
                .orElse(HttpStatus.SERVICE_UNAVAILABLE)
                .equals(expectedResponseCode);

        Optional<BaseResponseDto> baseResponse = Optional.of( mapObjectFromString(

                response.map(ResponseEntity::getBody)
                        .orElse(""),
                BaseResponseDto.class
        ));

        isSuccessfulRequest &= baseResponse.map(BaseResponseDto::getSuccess)
                .orElse(false);

        return this;
    }

    public <T> T getResponseBody(Class<T> classType) throws Exception {

        return mapObjectFromString(

                response.map(ResponseEntity::getBody)
                        .orElse(""),
                classType
        );
    }
}
