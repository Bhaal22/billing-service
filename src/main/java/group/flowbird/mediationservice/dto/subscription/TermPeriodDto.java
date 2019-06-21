package group.flowbird.mediationservice.dto.subscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TermPeriodDto {

    /**
     * Duration of the renewal term in months, years, days, or weeks, depending on the value of the periodType field.
     */
    @JsonProperty("period")
    private Integer period;

    /**
     * Unit of time that the renewal term is measured in.
     * Possible values: "Month" "Year" "Day" "Week"
     */
    @JsonProperty("periodType")
    private String periodType;
}
