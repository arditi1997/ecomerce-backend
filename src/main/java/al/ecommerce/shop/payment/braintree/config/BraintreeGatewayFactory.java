package al.ecommerce.shop.payment.braintree.config;

import com.braintreegateway.BraintreeGateway;

import java.util.Map;

public class BraintreeGatewayFactory {
    public static BraintreeGateway fromConfigMapping(Map<String, String> mapping) {
        return new BraintreeGateway(mapping.get("BT_ENVIRONMENT"), mapping.get("BT_MERCHANT_ID"),
                mapping.get("BT_PUBLIC_KEY"), mapping.get("BT_PRIVATE_KEY"));
    }
}
