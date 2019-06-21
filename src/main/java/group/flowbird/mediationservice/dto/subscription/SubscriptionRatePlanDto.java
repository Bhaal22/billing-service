package group.flowbird.mediationservice.dto.subscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionRatePlanDto {

    /**
     * ID of a product rate plan for this subscription from product catalog.
     */
    @JsonProperty("productRatePlanId")
    private String productRatePlanId;
}
