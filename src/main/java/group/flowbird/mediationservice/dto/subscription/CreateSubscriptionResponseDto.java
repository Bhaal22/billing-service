package group.flowbird.mediationservice.dto.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateSubscriptionResponseDto {

    @JsonProperty("success")
    private boolean success;

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
