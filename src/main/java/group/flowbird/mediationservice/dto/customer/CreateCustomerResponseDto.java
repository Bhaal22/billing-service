package group.flowbird.mediationservice.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateCustomerResponseDto {

    @JsonProperty("Success")
    private boolean isSuccess;

    @JsonProperty("accountId")
    private String accountId;

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("billToContactId")
    private String billToContactId;

    @JsonProperty("soldToContactId")
    private String soldToContactId;

    @JsonProperty("paymentMethodId")
    private String paymentMethodId;
}
