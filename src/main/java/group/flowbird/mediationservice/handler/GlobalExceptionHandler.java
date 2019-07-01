package group.flowbird.mediationservice.handler;

import group.flowbird.mediationservice.dto.ErrorDetailsDto;
import group.flowbird.mediationservice.dto.ErrorDetailsDto.ErrorDetailsBuilder;
import group.flowbird.mediationservice.dto.ErrorResponseDto;
import group.flowbird.mediationservice.dto.ErrorResponseDto.ErrorResponseBuilder;
import group.flowbird.mediationservice.util.RestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Map<Class<?>, HttpStatus> map;
    {
        map = new HashMap<>();
        map.put(Exception.class, HttpStatus.INTERNAL_SERVER_ERROR);
        map.put(RuntimeException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        map.put(RestClientException.class, HttpStatus.SERVICE_UNAVAILABLE);
        map.put(MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDto> handleException(Exception ex, WebRequest request) {

        log.error("Exception occurred in: " + request.getDescription(false) + "\nError message: " + ex.getMessage());

        return new ResponseEntity<>(

                new ErrorDetailsBuilder()
                        .setMessage(ex.getMessage())
                        .build(),
                map.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetailsDto> handleException(RuntimeException ex, WebRequest request) {

        log.error("Exception occurred in: " + request.getDescription(false) + "\nError message: " + ex.getMessage());

        return new ResponseEntity<>(

                new ErrorDetailsBuilder()
                        .setMessage(ex.getMessage())
                        .build(),
                map.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponseDto> handleException(HttpClientErrorException ex, WebRequest request) {

        Optional<String> responseText = Optional.of( ex.getResponseBodyAsString() );
        log.error("Received error response from zuora for: "
                + request.getDescription(false)
                + "\nError message: "
                + responseText.orElse("No Error or Unknown error description returned by zuora!"));

        ErrorResponseDto response = responseText.map(val -> {
            try {
                return RestUtils.mapObjectFromString(val, ErrorResponseDto.class);
            } catch (Exception e) {
                return buildUnknownErrorDescription(val);
            }
        }).orElse( buildUnknownErrorDescription("No Error or Unknown error description returned by zuora!") );

        return new ResponseEntity<>(

                response,
                map.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }

    private ErrorResponseDto buildUnknownErrorDescription(String description) {

        ErrorDetailsDto errorDetails = new ErrorDetailsBuilder()
                .setCode("No Error Code")
                .setMessage(description)
                .build();

        return new ErrorResponseBuilder()
                .addError(errorDetails)
                .build();
    }
}
