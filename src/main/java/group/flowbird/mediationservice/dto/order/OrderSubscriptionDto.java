package group.flowbird.mediationservice.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderSubscriptionDto {

    /**
     * Leave this empty to represent new subscription creation.
     * Specify a subscription number to update an existing subscription.
     */
    @JsonProperty("subscriptionNumber")
    private String subscriptionNumber;

    /**
     * The actions to be applied to the subscription.
     * Order actions will be stored with the sequence when it was provided in the request.
     */
    @JsonProperty("orderActions")
    List<OrderActionDto> orderActions;
}
