package group.flowbird.mediationservice.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import group.flowbird.mediationservice.dto.BaseResponseDto;
import lombok.Data;

@Data
public class CreateCustomerResponseDto extends BaseResponseDto {

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
