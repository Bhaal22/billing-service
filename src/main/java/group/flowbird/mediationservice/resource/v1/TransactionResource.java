package group.flowbird.mediationservice.resource.v1;

import group.flowbird.mediationservice.dto.transaction.CreateTransactionResponseDto;
import group.flowbird.mediationservice.dto.transaction.TransactionDto;
import group.flowbird.mediationservice.service.MediationService;
import group.flowbird.mediationservice.validation.ValidationGroup.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rest/v1/transaction")
public class TransactionResource {

    @Autowired
    MediationService mediationService;

    /**
     * Creates usage in zuora with the given transaction information
     * @param transaction
     * @return If successful: 200 OK with {@link CreateTransactionResponseDto} in the response body,
     * If failed: Corresponding Http error code with @{@link group.flowbird.mediationservice.dto.ErrorDetailsDto} in the response body.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createTransaction(@Validated(Create.class) @RequestBody TransactionDto transaction) throws Exception {

        log.info("Received request to create transaction, CustomerID: " + transaction.getAccountId());

        ResponseEntity<?> response = mediationService.createTransactionInZuora(transaction);

        log.info( String.format("Received Zuora response status: %s, for CustomerID: %s, ChargeID: %s",
                response.getStatusCode().toString(),
                transaction.getAccountId(),
                transaction.getChargeId()) );

        return response;
    }
}
