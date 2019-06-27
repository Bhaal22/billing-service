package group.flowbird.mediationservice.resource.v1;

import group.flowbird.mediationservice.dto.customer.CreateCustomerResponseDto;
import group.flowbird.mediationservice.dto.customer.CustomerDto;
import group.flowbird.mediationservice.service.MediationService;
import group.flowbird.mediationservice.validation.ValidationGroup.Create;
import group.flowbird.mediationservice.validation.ValidationGroup.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createCustomer(@Validated(Create.class) @RequestBody CustomerDto customer) throws Exception {

        log.info("Received request to create customer, CustomerID: " + customer.getCustomerID());
        ResponseEntity<?> response = mediationService.createCustomer(customer);

        log.info(String.format("Received Zuora response status: %s, for CustomerID: %s", response.getStatusCode().toString(), customer.getCustomerID()));

        if (response.getBody() instanceof CreateCustomerResponseDto) {
            mediationService.createOrder(
                    ((CreateCustomerResponseDto) response.getBody()).getAccountNumber()
            );
        }
        return response;
    }

    /**
     *
     * @param customerID
     * @param customer
     * @return
     */
    @PutMapping(value = "/{customerID}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> updateCustomer(@PathVariable String customerID,
                                            @Validated(Update.class) @RequestBody CustomerDto customer) throws Exception {

        log.info("Received request to update customer, CustomerID: " + customerID);
        ResponseEntity<?> response = mediationService.updateCustomer(customerID, customer);

        log.info(String.format("Received update customer response from zuora, status: %s, CustomerID: %s",
                response.getStatusCode().toString(),
                customerID));

        return response;
    }
}
