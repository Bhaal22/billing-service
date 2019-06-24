package group.flowbird.mediationservice.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ContactDto {

    /**
     * First address line, 255 characters or less.
     */
    @JsonProperty("address1")
    private String address;

    /**
     * City, 40 characters or less.
     */
    @JsonProperty("city")
    private String city;

    /**
     * Country, 32 characters or less.
     * Must be a valid country name or abbreviation.
     */
    @JsonProperty("country")
    private String country;

    /**
     * First name, 100 characters or less.
     */
    @NotEmpty
    @JsonProperty("firstName")
    private String firstName;

    /**
     * Last name, 100 characters or less.
     */
    @NotEmpty
    @JsonProperty("lastName")
    private String lastName;

    /**
     * Work email address, 80 characters or less.
     */
    @JsonProperty("workEmail")
    private String workEmail;

    /**
     * Zip code, 20 characters or less.
     */
    @JsonProperty("zipCode")
    private String zipCode;
}
