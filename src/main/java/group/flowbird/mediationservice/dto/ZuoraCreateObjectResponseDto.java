package group.flowbird.mediationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ZuoraCreateObjectResponseDto {

    @JsonProperty("Success")
    private boolean isSuccess;

    @JsonProperty("Id")
    private String accountId;
}
