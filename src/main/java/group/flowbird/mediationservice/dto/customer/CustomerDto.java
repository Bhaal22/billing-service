package group.flowbird.mediationservice.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

    @JsonProperty("customerID__c")
    private String customerID;

    @JsonProperty("autoPay")
    private boolean autoPay;

    @JsonProperty("batch")
    private String batch;

    @JsonProperty("billCycleDay")
    private int billCycleDay;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("name")
    private String name;

    @JsonProperty("paymentTerm")
    private String paymentTerm;

    @JsonProperty("status")
    private String status;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("invoiceDeliveryPrefsEmail")
    private boolean invoiceDeliveryPrefsEmail;

    @JsonProperty("invoiceDeliveryPrefsPrint")
    private boolean invoiceDeliveryPrefsPrint;

    @JsonProperty("billToContact")
    private ContactDto billToContact;
}
