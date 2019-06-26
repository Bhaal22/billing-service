package group.flowbird.mediationservice.dto.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    /**
     * Unique identifier of a customer in Zuora
     */
    @NotEmpty
    @JsonProperty("AccountId")
    private String accountId;

    /**
     * The original ID of the rate plan charge related to the transaction
     */
    @JsonProperty("ChargeId")
    private String chargeId;

    /**
     * TODO
     */
    @JsonProperty("ChargeNumber")
    private String chargeNumber;

    /**
     * TODO
     */
    @NotEmpty
    @JsonProperty("Description")
    private String description;

    /**
     * TODO
     */
    @NotEmpty
    @JsonProperty("EndDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date endDateTime;

    /**
     * TODO
     */
    @JsonProperty("Quantity")
    private Double quantity;

    /**
     * TODO
     */
    @JsonProperty("StartDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date startDateTime;

    /**
     * TODO
     */
    @JsonProperty("SubscriptionId")
    private String subscriptionId;

    /**
     * TODO
     */
    @JsonProperty("SubscriptionNumber")
    private String subscriptionNumber;

    /**
     * TODO
     */
    @NotEmpty
    @JsonProperty("UOM")
    private String uom;

    /**
     * TODO
     */
    @NotEmpty
    @JsonProperty("U_Duration__c")
    private String duration;
}
