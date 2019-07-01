package group.flowbird.mediationservice.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestUtils {

    public static <T> T mapObjectFromString(String responseString, Class<T> classType) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if(classType.equals(String.class)) {
            return (T) responseString;
        }
        return (T) objectMapper.readValue(responseString, classType);
    }
}
