package al.ecommerce.shop.payment.paypal.config;

import al.ecommerce.shop.payment.paypal.model.PaypalProperties;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaypalConfig {

    @Autowired
    PaypalProperties paypalProperties;

    @Bean
    public Map paypalSdkConfig() {
        Map configMap = new java.util.HashMap<>();
        configMap.put("mode", paypalProperties.getMode());
        return configMap;
    }

    @Bean
    public OAuthTokenCredential oAuthTokenCredential() {
        return new OAuthTokenCredential(paypalProperties.getClient().getId(), paypalProperties.getClient().getSecret(), paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }
}
