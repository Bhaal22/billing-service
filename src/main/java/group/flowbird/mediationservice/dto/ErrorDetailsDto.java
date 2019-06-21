package group.flowbird.mediationservice.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
@Slf4j
public class ErrorDetailsDto {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    public static class ErrorDetailsBuilder {

        private String code;
        private String message;

        public ErrorDetailsBuilder setMessage(String message) {

            this.message = message;
            return this;
        }

        public ErrorDetailsBuilder setCode(String code) {

            this.code = code;
            return this;
        }

        public ErrorDetailsDto build() {

            ErrorDetailsDto errorDetails = new ErrorDetailsDto();
            errorDetails.setMessage(this.message);
            errorDetails.setCode(this.code);
            return errorDetails;
        }
    }
}
