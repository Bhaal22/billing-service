package group.flowbird.mediationservice.config;

import lombok.Data;

@Data
public class ZuoraEndpoints {

    private String createCustomer;
    private String updateCustomer;
    private String getCustomer;
    private String getCustomerSubscriptions;
    private String createOrder;
    private String getProductCatalog;
    private String createUsage;
    private String createBillingRun;
}
