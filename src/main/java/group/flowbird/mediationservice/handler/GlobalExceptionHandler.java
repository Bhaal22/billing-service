package group.flowbird.mediationservice.handler;

import group.flowbird.mediationservice.dto.ErrorDetailsDto;
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

                new ErrorDetailsDto.ErrorDetailsBuilder()
                        .setMessage(ex.getMessage())
                        .build(),
                map.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetailsDto> handleException(RuntimeException ex, WebRequest request) {

        log.error("Exception occurred in: " + request.getDescription(false) + "\nError message: " + ex.getMessage());

        return new ResponseEntity<>(

                new ErrorDetailsDto.ErrorDetailsBuilder()
                        .setMessage(ex.getMessage())
                        .build(),
                map.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }
}
