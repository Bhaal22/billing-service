package group.flowbird.mediationservice.client;

import group.flowbird.mediationservice.dto.ErrorDetailsDto;
import group.flowbird.mediationservice.dto.ErrorResponseDto;
import group.flowbird.mediationservice.util.ZuoraResponseParser;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
@Setter
public class ZuoraClient {

    RestClient restClient;

    String endpoint;
    HttpMethod requestMethod;
    HttpEntity<?> payload;
    HttpStatus expectedResponseCode;
    Class<?> responseClassType;

    private ZuoraClient() {
        //almost empty!
    }

    /**
     * Performs a request to the already set @endpoint and parse the response received from zuora
     * @return ResponseEntity with @responseClassType if Successful or @{@link ErrorDetailsDto} if failed.
     * @throws Exception, in case of 4xx response HttpClientErrorException is thrown and in case of 5xx response HttpServerErrorException
     */
    public ResponseEntity<?> performRequest() throws Exception {

        ResponseEntity<String> zuoraResponse = restClient.performRequest(
                payload,
                requestMethod,
                endpoint
        );

        return parseResponse(zuoraResponse);
    }

    private ResponseEntity<?> parseResponse(ResponseEntity<String> response) throws Exception {

        ZuoraResponseParser parser = new ZuoraResponseParser()
                .parseResponse(response, expectedResponseCode);

        if (parser.isSuccessfulRequest()) {

            return new ResponseEntity<>(
                    parser.getResponseBody(responseClassType),
                    expectedResponseCode
            );
        }

        ErrorResponseDto errorDetails = parser.getResponseBody(ErrorResponseDto.class);
        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.OK
        );
    }

    public static class ZuoraClientBuilder {

        RestClient restClient;

        String endpoint;
        HttpMethod requestMethod;
        HttpEntity<?> payload;
        HttpStatus expectedResponseCode;
        Class<?> responseClassType;

        public ZuoraClientBuilder(RestClient restClient) {

            this.restClient = restClient;
            payload = null;
            expectedResponseCode = HttpStatus.OK;
            responseClassType = String.class;
        }

        public ZuoraClientBuilder setEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public ZuoraClientBuilder setRequestMethod(HttpMethod requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public <T> ZuoraClientBuilder setPayload(T payload) {
            this.payload = new HttpEntity<>(payload);
            return this;
        }

        public ZuoraClientBuilder setExpectedResponseCode(HttpStatus expectedResponseCode) {
            this.expectedResponseCode = expectedResponseCode;
            return this;
        }

        public ZuoraClientBuilder setResponseClassType(Class<?> responseClassType) {
            this.responseClassType = responseClassType;
            return this;
        }

        public ZuoraClient build() {

            ZuoraClient client = new ZuoraClient();

            client.setRestClient(this.restClient);
            client.setEndpoint(this.endpoint);
            client.setRequestMethod(this.requestMethod);
            client.setPayload(this.payload);
            client.setExpectedResponseCode(this.expectedResponseCode);
            client.setResponseClassType(this.responseClassType);

            return client;
        }
    }
}
