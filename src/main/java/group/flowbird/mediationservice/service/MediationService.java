package group.flowbird.mediationservice.service;

import group.flowbird.mediationservice.client.RestClient;
import group.flowbird.mediationservice.client.ZuoraClient;
import group.flowbird.mediationservice.config.ZuoraEndpoints;
import group.flowbird.mediationservice.dto.customer.CreateCustomerResponseDto;
import group.flowbird.mediationservice.dto.customer.CustomerDto;
import group.flowbird.mediationservice.dto.order.OrderActionDto;
import group.flowbird.mediationservice.dto.order.OrderDto;
import group.flowbird.mediationservice.dto.order.OrderSubscriptionDto;
import group.flowbird.mediationservice.dto.order.TriggerDateDto;
import group.flowbird.mediationservice.dto.subscription.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Slf4j
@Service
public class MediationService {

    @Autowired
    private ZuoraEndpoints zuoraEndpoints;

    @Autowired
    private RestClient restClient;

    /**
     * Makes create account API call to zuora and parse the response
     * @param customer
     * @return ResponseEntity with {@link CreateCustomerResponseDto} If successful,
     * or {@link group.flowbird.mediationservice.dto.ErrorDetailsDto} If failed
     * @throws Exception
     */
    public ResponseEntity<?> createCustomerInZuora(CustomerDto customer) throws Exception {

        ZuoraClient client = new ZuoraClient.ZuoraClientBuilder(restClient)
                .setRequestMethod(HttpMethod.POST)
                .setEndpoint(zuoraEndpoints.getCreateCustomer())
                .setPayload(customer)
                .setExpectedResponseCode(HttpStatus.OK)
                .setResponseClassType(CreateCustomerResponseDto.class)
                .build();

        log.info(String.format("Sending create account request to Zuora, CustomerID: ", customer.getCustomerID()));

        return client.performRequest();
    }

    public ResponseEntity<?> createOrder(OrderDto order) throws Exception {

        ZuoraClient client = new ZuoraClient.ZuoraClientBuilder(restClient)
                .setRequestMethod(HttpMethod.POST)
                .setEndpoint(zuoraEndpoints.getCreateOrder())
                .setPayload(order)
                .setExpectedResponseCode(HttpStatus.OK)
                .setResponseClassType(CreateSubscriptionResponseDto.class)
                .build();

        log.info("Sending create order request to Zuora, AccountNumber: " + order.getExistingAccountNumber());

        return client.performRequest();
    }

    public ResponseEntity<?> createOrder(String accountKey) throws Exception {

        return createOrder( createOrderObjectWithBasicSubscription(accountKey) );
    }

    private OrderDto createOrderObjectWithBasicSubscription(String accountKey) {

        OrderDto order = new OrderDto();
        order.setExistingAccountNumber(accountKey);
        order.setOrderDate(new Date());
        OrderSubscriptionDto orderSubscription = new OrderSubscriptionDto();
        order.setOrderSubscriptions(Arrays.asList(orderSubscription));

        //orderSubscription
        OrderActionDto orderAction = new OrderActionDto();
        orderSubscription.setOrderActions(Arrays.asList(orderAction));

        //orderAction
        orderAction.setType("CreateSubscription");
        TriggerDateDto triggerDate = new TriggerDateDto();
        orderAction.setTriggerDates(Arrays.asList(triggerDate));
        SubscriptionDto subscription = new SubscriptionDto();
        orderAction.setSubscription(subscription);

        //triggerDate
        triggerDate.setName("ContractEffective");
        triggerDate.setTriggerDate(new Date());

        //subscription
        subscription.setSubscriptionOwnerAccountNumber(accountKey);
        subscription.setNotes("Initial Subscription");
        SubscriptionRatePlanDto subscriptionRatePlan = new SubscriptionRatePlanDto();
        subscription.setSubscribeToRatePlans(Arrays.asList(subscriptionRatePlan));
        SubscriptionTermDto subscriptionTerm = new SubscriptionTermDto();
        subscription.setTerms(subscriptionTerm);

        //subscriptionRatePlan
        subscriptionRatePlan.setProductRatePlanId("8adc8f996ade4a0c016ade83b7d20559");

        //subscriptionTerm
        subscriptionTerm.setAutoRenew(false);
        TermDto initialTerm = new TermDto();
        subscriptionTerm.setInitialTerm(initialTerm);

        //initialTerm
        initialTerm.setTermType("EVERGREEN");
        initialTerm.setStartDate(new Date());

        return order;
    }
}
