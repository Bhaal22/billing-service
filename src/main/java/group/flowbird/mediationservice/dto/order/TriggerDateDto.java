package group.flowbird.mediationservice.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TriggerDateDto {

    /**
     * Name of the trigger date of the order action.
     * Possible values: Enum:"ContractEffective" "ServiceActivation" "CustomerAcceptance"
     */
    @JsonProperty("name")
    private String name;

    /**
     * Trigger date in yyyy-MM-dd format.
     */
    @JsonProperty("triggerDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date triggerDate;
}
