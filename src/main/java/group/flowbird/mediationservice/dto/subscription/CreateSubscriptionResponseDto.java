package group.flowbird.mediationservice.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import group.flowbird.mediationservice.dto.BaseResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CreateSubscriptionResponseDto extends BaseResponseDto {

    /**
     * Generated subscription ID from Zuora
     */
    @JsonProperty("subscriptionId")
    private String subscriptionId;

    /**
     * Generated subscription number from Zuora
     */
    @JsonProperty("subscriptionNumber")
    private String subscriptionNumber;

    /**
     * Monthly recurring revenue of the subscription.
     */
    @JsonProperty("contractedMrr")
    private double contractedMrr;

    /**
     * Total contracted value of the subscription.
     */
    @JsonProperty("totalContractedValue")
    private double totalContractedValue;
}
