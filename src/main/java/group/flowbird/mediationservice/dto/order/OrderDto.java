package group.flowbird.mediationservice.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    /**
     * The Zuora account number that this order will be created under.
     */
    @JsonProperty("existingAccountNumber")
    private String existingAccountNumber;

    /**
     * The date when the order is signed.
     * All the order actions under this order will use this order date as the contract effective date if the contract effective date field is skipped or its value is left as null.
     */
    @NotNull
    @JsonProperty("orderDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date orderDate;

    /**
     * The order number of the new order.
     * If not provided, Zuora will auto-generate a number for this order.
     */
    @JsonProperty("orderNumber")
    private String orderNumber;

    /**
     * Each item includes a set of order actions, which will be applied to the same base subscription.
     */
    @NotNull
    @JsonProperty("subscriptions")
    List<OrderSubscriptionDto> orderSubscriptions;
}
