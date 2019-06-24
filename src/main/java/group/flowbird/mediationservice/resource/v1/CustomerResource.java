package group.flowbird.mediationservice.resource.v1;

import group.flowbird.mediationservice.dto.customer.CreateCustomerResponseDto;
import group.flowbird.mediationservice.dto.customer.CustomerDto;
import group.flowbird.mediationservice.service.MediationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/rest/v1/customer")
public class CustomerResource {

    @Autowired
    MediationService mediationService;

    /**
     * Creates account in zuora with the given customer information
     * @param customer
     * @return If successful: 200 OK with {@link CreateCustomerResponseDto} in the response body,
     * If failed: Corresponding Http error code with @{@link group.flowbird.mediationservice.dto.ErrorDetailsDto} in the response body.
     */
    @PostMapping(value = "/create",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto customer) throws Exception {

        log.info("Received request to create customer, CustomerID: " + customer.getCustomerID());

        ResponseEntity<?> response = mediationService.createCustomerInZuora(customer);

        log.info(String.format("Received Zuora response status: %s, for CustomerID: %s", response.getStatusCode().toString(), customer.getCustomerID()));

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            mediationService.createOrder(
                    ((CreateCustomerResponseDto) response.getBody()).getAccountNumber()
            );
        }
        return response;
    }
}
