package group.flowbird.mediationservice.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContactDto {

    @JsonProperty("address1")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("workEmail")
    private String workEmail;

    @JsonProperty("zipCode")
    private String zipCode;
}
