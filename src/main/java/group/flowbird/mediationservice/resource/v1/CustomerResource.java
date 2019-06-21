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

@Slf4j
@RestController
@RequestMapping("/rest/v1/customer")
public class CustomerResource {

    @Autowired
    MediationService mediationService;

    @PostMapping(value = "/create",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customer) throws Exception {

        ResponseEntity<?> response = mediationService.createCustomerInZuora(customer);

        if (response.getStatusCode().equals(HttpStatus.CREATED)) {
            mediationService.createOrder(
                    ((CreateCustomerResponseDto) response.getBody()).getAccountNumber()
            );
        }
        return response;
    }
}
