package group.flowbird.mediationservice.dto.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateTransactionResponseDto {

    @JsonProperty("Success")
    private boolean success;

    @JsonProperty("Id")
    private String usageId;
}
