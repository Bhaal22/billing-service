package group.flowbird.mediationservice.config;

import lombok.Data;

@Data
public class ZuoraConnectionParams {

    private String baseUrl;
    private String clientId;
    private String secretKey;
    private String accessTokenURL;
    private String authorizationURL;
    private String tokenName;
    private String grantType;
}
