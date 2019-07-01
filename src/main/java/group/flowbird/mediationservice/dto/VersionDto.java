package group.flowbird.mediationservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class VersionDto {

    @JsonProperty("git.branch")
    private String branch;
    @JsonProperty("git.commit.id")
    private String commitID;

    public String toString() {
        return String.format("Version: %s/%s", commitID, branch);
    }
}
