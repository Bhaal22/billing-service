package group.flowbird.mediationservice.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import group.flowbird.mediationservice.validation.ValidationGroup.NullOrNotBlank;
import group.flowbird.mediationservice.validation.ValidationGroup.Create;
import group.flowbird.mediationservice.validation.ValidationGroup.Update;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

    /**
     * Unique identifier of a customer in MPP
     */
    @NotEmpty(groups = Create.class)
    @Null(groups = Update.class)
    @JsonProperty("customerID__c")
    private String customerID;

    /**
     * Whether future payments are to be automatically billed when they are due.
     *
     * If this field is set to true, you must specify either the creditCard field or the hpmCreditCardPaymentMethodId field, but not both.
     * If this field is set to false, you can specify neither the creditCard field nor the hpmCreditCardPaymentMethodId field.
     */
    @JsonProperty("autoPay")
    private Boolean autoPay;

    /**
     * The alias name that will be used to group customers while executing a billing run.
     * A string of 50 characters or less.
     */
    @JsonProperty("batch")
    private String batch;

    /**
     * The account's bill cycle day (BCD), when bill runs generate invoices for the account.
     * Specify any day of the month (1-31, where 31 = end-of-month), or 0 for auto-set.
     */
    @Null(groups = Update.class)
    @JsonProperty("billCycleDay")
    private Long billCycleDay;

    /**
     * One of the currency that is defined in Billing Settings in the Zuora UI.
     */
    @NotEmpty(groups = Create.class)
    @Null(groups = Update.class)
    @JsonProperty("currency")
    private String currency;

    /**
     * Account name, up to 255 characters.
     */
    @NotEmpty(groups = Create.class)
    @NullOrNotBlank(groups = Update.class)
    @JsonProperty("name")
    private String name;

    /**
     * Payment terms for this account.
     * Possible values are: Due Upon Receipt, Net 30, Net 60, Net 90.
     */
    @Null(groups = Update.class)
    @JsonProperty("paymentTerm")
    private String paymentTerm;

    /**
     * A string of up to 65,535 characters.
     */
    @JsonProperty("notes")
    private String notes;

    /**
     * Whether the customer wants to receive invoices through email.
     * If not specified, it will be set to false.
     */
    @JsonProperty("invoiceDeliveryPrefsEmail")
    private Boolean invoiceDeliveryPrefsEmail;

    /**
     * Whether the customer wants to receive printed invoices, such as through postal mail.
     * If not specified, it will be set to false.
     */
    @JsonProperty("invoiceDeliveryPrefsPrint")
    private Boolean invoiceDeliveryPrefsPrint;

    /**
     * Container for bill-to contact information for this account.
     * If you do not provide a sold-to contact, the bill-to contact is copied to sold-to contact.
     * Once the sold-to contact is created, changes to billToContact will not affect soldToContact and vice versa.
     */
    @NotNull(groups = Create.class)
    @JsonProperty("billToContact")
    private ContactDto billToContact;
}
