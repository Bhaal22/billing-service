package group.flowbird.mediationservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto extends BaseResponseDto {

    @JsonProperty("Errors")
    List<ErrorDetailsDto> errors;

    @JsonProperty("reasons")
    List<ErrorDetailsDto> reasons;

    public static class ErrorResponseBuilder {

        List<ErrorDetailsDto> errors = Collections.EMPTY_LIST;

        public ErrorResponseBuilder addError(ErrorDetailsDto errorDetails) {

            errors.add(errorDetails);
            return this;
        }

        public ErrorResponseDto build() {

            ErrorResponseDto errorResponse = new ErrorResponseDto();
            errorResponse.setErrors(errors);
            return errorResponse;
        }
    }
}
