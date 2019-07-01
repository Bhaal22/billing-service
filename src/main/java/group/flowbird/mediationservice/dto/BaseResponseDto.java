package group.flowbird.mediationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseResponseDto {

    /**
     * All response from zuora has Success filed indicating the status of the request
     */
    @JsonProperty("Success")
    private Boolean success;

    public BaseResponseDto() {
        success = false;
    }
}
