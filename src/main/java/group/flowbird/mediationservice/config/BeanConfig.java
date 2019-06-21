package group.flowbird.mediationservice.config;

import group.flowbird.mediationservice.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class BeanConfig {

    @Autowired
    private ZuoraConnectionParams zuoraConnectionParams;

    @Autowired
    private OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    @Autowired
    OAuth2RestTemplate restTemplate;

    @Bean
    @ConfigurationProperties("zuora.connection-params")
    public ZuoraConnectionParams zuoraConnectionParams() {

        return new ZuoraConnectionParams();
    }

    @Bean
    @ConfigurationProperties("zuora.endpoints")
    public ZuoraEndpoints zuoraEndpoints() {

        return new ZuoraEndpoints();
    }

    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {

        ClientCredentialsResourceDetails clientCredentials = new ClientCredentialsResourceDetails();
        clientCredentials.setClientId(zuoraConnectionParams.getClientId());
        clientCredentials.setClientSecret(zuoraConnectionParams.getSecretKey());
        clientCredentials.setAccessTokenUri(zuoraConnectionParams.getAccessTokenURL());
        clientCredentials.setGrantType(zuoraConnectionParams.getGrantType());
        return clientCredentials;
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext context) {

        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, context);
    }

    @Bean
    RestClient restClient() {

        return new RestClient(restTemplate);
    }
}
