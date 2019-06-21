package group.flowbird.mediationservice.dto.subscription;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionDto {

    /**
     * Subscription number of the subscription.
     * If you do not set this field, Zuora will generate the subscription number.
     */
    @JsonProperty("subscriptionNumber")
    private String subscriptionNumber;

    /**
     * Specifies whether the subscription appears on a separate invoice when Zuora generates invoices
     */
    @JsonProperty("invoiceSeparately")
    private Boolean invoiceSeparately;

    /**
     * Customer account number or ID in Zuora
     */
    @JsonProperty("subscriptionOwnerAccountNumber")
    private String subscriptionOwnerAccountNumber;

    /**
     * Container for one or more rate plans for this subscription
     */
    @JsonProperty("subscribeToRatePlans")
    private List<SubscriptionRatePlanDto> subscribeToRatePlans;

    /**
     * Container for the terms and renewal settings of the subscription.
     */
    @JsonProperty("terms")
    private SubscriptionTermDto terms;

    /**
     * Notes about the subscription. These notes are only visible to Zuora users.
     */
    @JsonProperty("notes")
    private String notes;
}
