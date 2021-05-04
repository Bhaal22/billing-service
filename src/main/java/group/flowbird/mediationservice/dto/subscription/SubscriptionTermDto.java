package group.flowbird.mediationservice.dto.subscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionTermDto {

    /**
     * If true, this subscription automatically renews at the end of the subscription term
     */
    @JsonProperty("autoRenew")
    private Boolean autoRenew;

    /**
     * Information about the first term of the subscription.
     */
    @NotNull
    @JsonProperty("initialTerm")
    private TermDto initialTerm;

    /**
     * Specifies the type of the terms that follow the first term if the subscription is renewed.
     * Only applicable if the type of the first term is TERMED.
     * Possible values: "RENEW_WITH_SPECIFIC_TERM" "RENEW_TO_EVERGREEN"
     */
    @JsonProperty("renewalSetting")
    private String renewalSetting;

    /**
     * List of renewal terms of the subscription.
     * Only applicable if the type of the first term is TERMED and the value of the renewalSetting field is RENEW_WITH_SPECIFIC_TERM.
     */
    @JsonProperty("renewalTerms")
    List<TermPeriodDto> renewalTerms;
}
