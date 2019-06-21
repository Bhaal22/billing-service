package group.flowbird.mediationservice.dto.subscription;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TermDto {

    /**
     * Duration of the first term in months, years, days, or weeks, depending on the value of the periodType field.
     * Only applicable if the value of the termType field is TERMED.
     */
    @JsonProperty("period")
    private Integer period;

    /**
     * Unit of time that the first term is measured in.
     * Only applicable if the value of the termType field is TERMED.
     */
    @JsonProperty("periodType")
    private String periodType;

    /**
     * Start date of the first term, in YYYY-MM-DD format.
     */
    @JsonProperty("startDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * Type of the first term. If the value of this field is TERMED, the first term has a predefined duration based on the value of the period field.
     * If the value of this field is EVERGREEN, the first term does not have a predefined duration.
     * Possible Value: "TERMED" "EVERGREEN"
     */
    @JsonProperty("termType")
    private String termType;
}
