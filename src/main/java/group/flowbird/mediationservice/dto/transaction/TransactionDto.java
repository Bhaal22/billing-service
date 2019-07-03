package group.flowbird.mediationservice.dto.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import group.flowbird.mediationservice.validation.ValidationGroup.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    /**
     * Unique identifier of a customer in Zuora
     */
    @NotEmpty(groups = Create.class)
    @JsonProperty("AccountId")
    private String accountId;

    /**
     * Unique transaction ID that is used to represent a parking in MPP.
     * Zuora will combine parking fee and convenience fee usage in the invoice using this transaction ID
     */
    @NotEmpty(groups = Create.class)
    @JsonProperty("U_TransactionId__c")
    private String transactionId;

    /**
     * The original ID of the rate plan charge related to the transaction
     */
    @JsonProperty("ChargeId")
    private String chargeId;

    /**
     * The end date and time of a range of time when usage is tracked. Use this field for reporting; this field doesn't affect usage calculation.
     */
    @JsonProperty("EndDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date endDateTime;

    /**
     * Indicates the number of units used. Character limit: 16 Values: a valid decimal amount equal to or greater than 0
     */
    @NotNull(groups = Create.class,
            message = "Quantity must be a valid decimal amount equal to or greater than 0")
    @JsonProperty("Quantity")
    private Double quantity;

    /**
     * The start date and time from when usage is tracked.
     * Zuora uses this field value to determine the usage date.
     * StartDateTime is displayed as transaction date in the invoice.
     * Unlike the EndDateTime, the StartDateTime field does affect usage calculation.
     */
    @NotNull(groups = Create.class,
            message = "StartDateTime must be a valid time provided in the following format: yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("StartDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date startDateTime;

    /**
     * Subscription ID of the corresponding customer, that contains the fees related to the usage data.
     */
    @JsonProperty("SubscriptionId")
    private String subscriptionId;

    /**
     * Specifies the units to measure usage.
     * Units of measure are configured in the web-based UI.
     * UOM are defined in "Billing Settings" -> "Customize Unit of Measures"
     */
    @NotEmpty(groups = Create.class)
    @JsonProperty("UOM")
    private String uom;

    /**
     * Parking duration that will be displayed in the invoice
     */
    @NotEmpty(groups = Create.class)
    @JsonProperty("U_Duration__c")
    private String duration;

    /**
     * License plate number of the vehicle
     */
    @JsonProperty("U_LicensePlate__c")
    private String licensePlate;

    /**
     * Parking location name
     */
    @JsonProperty("U_Location__c")
    private String location;
}
