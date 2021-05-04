package group.flowbird.mediationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CreateObjectResponseDto extends BaseResponseDto {

    @JsonProperty("Id")
    private String id;
}
