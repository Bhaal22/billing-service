package group.flowbird.mediationservice.handler;

import group.flowbird.mediationservice.dto.ErrorDetailsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Map<Class<?>, HttpStatus> map;
    {
        map = new HashMap<>();
        map.put(Exception.class, HttpStatus.INTERNAL_SERVER_ERROR);
        map.put(RuntimeException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        map.put(RestClientException.class, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDto> handleException(Exception ex) {

        return new ResponseEntity<>(

                new ErrorDetailsDto.ErrorDetailsBuilder()
                        .setMessage(ex.getMessage())
                        .build(),
                map.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetailsDto> handleException(RuntimeException ex) {

        return new ResponseEntity<>(

                new ErrorDetailsDto.ErrorDetailsBuilder()
                        .setMessage(ex.getMessage())
                        .build(),
                map.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }
}
