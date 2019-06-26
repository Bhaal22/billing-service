package group.flowbird.mediationservice.resource.v1;

import group.flowbird.mediationservice.dto.transaction.CreateTransactionResponseDto;
import group.flowbird.mediationservice.dto.transaction.TransactionDto;
import group.flowbird.mediationservice.service.MediationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @PostMapping(value = "/create",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto transaction) throws Exception {

        log.info("Received request to create transaction, AccountID: " + transaction.getAccountId());

        ResponseEntity<?> response = mediationService.createTransactionInZuora(transaction);

        log.info(String.format("Received Zuora response status: %s, for AccountID: %s, ChargeID: %s", response.getStatusCode().toString(), transaction.getAccountId(), transaction.getChargeId()));

       /* if (response.getStatusCode().equals(HttpStatus.OK)) {
            mediationService.createOrder( // TODO create Order ?
                    ((CreateTransactionResponseDto) response.getBody()).getUsageId()
            );
        }*/
        return response;
    }
}
