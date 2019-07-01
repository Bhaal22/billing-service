package group.flowbird.mediationservice.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import group.flowbird.mediationservice.dto.VersionDto;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;;
import org.springframework.web.bind.annotation.*;
import java.io.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
@Api(value = "Application Information")
public class ApplicationController {

    @RequestMapping(method={RequestMethod.GET},value={"/version"})
    public String getVersion() {
        return mapToVersion().toString();
    }

    @RequestMapping(method={RequestMethod.GET},value={"/name"})
    public String getApplicationName() {
        return "Mediation Service";
    }

    private String readFromInputStream() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("git.properties");
        StringBuilder propertyBuilder = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line;
            while ((line = br.readLine()) != null ) {
                propertyBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            log.info("git.properties resource is not found.");
        }

        return propertyBuilder.toString();
    }

    private VersionDto mapToVersion() {
        ObjectMapper objectMapper = new ObjectMapper();
        VersionDto version = new VersionDto();
        try {
            version = objectMapper.readValue(readFromInputStream(), VersionDto.class);
        } catch (IOException e) {
            log.info("There is no version information available.");
        }

        return version;
    }
}
