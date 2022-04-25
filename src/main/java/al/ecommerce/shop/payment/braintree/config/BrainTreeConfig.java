package al.ecommerce.shop.payment.braintree.config;

import al.ecommerce.shop.payment.braintree.model.BrainTreeProperties;
import com.braintreegateway.BraintreeGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BrainTreeConfig {

    @Autowired
    Environment env;
    @Autowired
    BrainTreeProperties brainTreeProperties;
    public static BraintreeGateway gateway;
    private static final Logger logger =  LoggerFactory.getLogger(BrainTreeConfig.class);

    @Bean
    public void brainTreeConfigs() {
        try {
            Map<String, String> mapping = new HashMap<>();
            mapping.put("BT_ENVIRONMENT", brainTreeProperties.getMode());
            mapping.put("BT_MERCHANT_ID", brainTreeProperties.getMerchantId());
            mapping.put("BT_PUBLIC_KEY", brainTreeProperties.getPublicKey());
            mapping.put("BT_PRIVATE_KEY", brainTreeProperties.getPrivateKey());
            gateway = BraintreeGatewayFactory.fromConfigMapping(mapping);
            logger.info(String.valueOf(gateway));
        } catch (NullPointerException e) {
            System.err.println("Could not load Braintree configuration from config file or system environment.");
            System.exit(1);
        }
    }
}

