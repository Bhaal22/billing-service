package group.flowbird.mediationservice.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import group.flowbird.mediationservice.dto.subscription.SubscriptionDto;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderActionDto {

    /**
     * Type of order action.
     *
     * Enum:"CreateSubscription" "TermsAndConditions" "AddProduct" "UpdateProduct" "RemoveProduct" "RenewSubscription" "CancelSubscription" "OwnerTransfer" "Suspend" "Resume"
     *
     * Unless the type of order action is RenewSubscription, you must use the corresponding field to provide information about the order action.
     * For example, if the type of order action is CreateSubscription, you must set the CreateSubscription field.
     */
    @JsonProperty("type")
    private String type;

    /**
     * Information about the subscription that will be created with this order.
     */
    @JsonProperty("createSubscription")
    private SubscriptionDto subscription;

    /**
     * Container for the contract effective, service activation, and customer acceptance dates of the order action.
     */
    @JsonProperty("triggerDates")
    private List<TriggerDateDto> triggerDates;
}
