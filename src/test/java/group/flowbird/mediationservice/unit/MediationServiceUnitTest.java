package group.flowbird.mediationservice.unit;

import group.flowbird.mediationservice.dto.transaction.CreateTransactionResponseDto;
import group.flowbird.mediationservice.dto.transaction.TransactionDto;
import group.flowbird.mediationservice.service.MediationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class MediationServiceUnitTest {

    private static final String PRIVATE_AUTH_KEY = "GZSYk7Zc";
    private static final int MESSAGE_TYPE_500 = 500;
    private static final int MESSAGE_TYPE_502 = 502;
    private static final int MESSAGE_TYPE_200 = 200;
    private static final int MESSAGE_TYPE_400 = 400;

    // Warning : this values may change
    private static final String ACCOUNT_ID = "8adcfb506b7935c8016b8ed0b84435a5";
    private static final String CHARGE_ID = "8adcfb506b7935c8016b8ed0c6ac35b6";
    private static final String SUBSCRIPTION_ID = "8adcfb506b7935c8016b8ed0c67335af";

    @Autowired
    private MediationService mediationService;

    @Test
    public void createTransactionInZuoraTestOk() {
        TransactionDto transactionDto = this.createTransactionDto();
        ResponseEntity<CreateTransactionResponseDto> responseDtoResponseEntity = ReflectionTestUtils.invokeMethod(mediationService, "createTransactionInZuora", transactionDto);

        assertEquals(MESSAGE_TYPE_200, responseDtoResponseEntity.getStatusCode().value());
        assertEquals(true, responseDtoResponseEntity.getBody().isSuccess());
    }

    @Test
    public void createTransactionInZuoraTestFailFieldAreMissing() {
        TransactionDto transactionDto = this.createTransactionDto();
        transactionDto.setUom(null);
        try {
            ReflectionTestUtils.invokeMethod(mediationService, "createTransactionInZuora", transactionDto); // have to throw HttpClientErrorException
            fail();
        } catch (HttpClientErrorException e){
            String expected = "{\"Errors\":[{\"Code\":\"INVALID_VALUE\",\"Message\":\"The Unit of Measure (UOM) is required.\"}],\"Success\":false}\n";
            assertEquals(expected, e.getResponseBodyAsString());
        }
    }

    @Test
    public void createTransactionInZuoraTestFailChargeNotFound() {
        TransactionDto transactionDto = this.createTransactionDto();
        transactionDto.setChargeId("xxxNotFoundChargeIdxxx");
        try {
            ReflectionTestUtils.invokeMethod(mediationService, "createTransactionInZuora", transactionDto); // have to throw HttpClientErrorException
            fail();
        } catch (HttpClientErrorException e){
            String expected = "{\"Errors\":[{\"Code\":\"INVALID_VALUE\",\"Message\":\"Invalid value for field ChargeId: xxxNotFoundChargeIdxxx\"}],\"Success\":false}\n";
            assertEquals(expected, e.getResponseBodyAsString());
        }
    }

    private TransactionDto createTransactionDto(){
        TransactionDto transactionDto = new TransactionDto();
        try {
            transactionDto.setAccountId(ACCOUNT_ID);
            transactionDto.setChargeId(CHARGE_ID);
            transactionDto.setSubscriptionId(SUBSCRIPTION_ID);
            transactionDto.setDescription("this is desc");
            transactionDto.setEndDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse("2016-11-20T04:41:36.000+01:00"));
            transactionDto.setQuantity(9.9);
            transactionDto.setStartDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse("2016-10-20T05:41:36.000+02:00"));
            transactionDto.setUom("Each");
            transactionDto.setDuration("1:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return transactionDto;
    }

}
