package al.ecommerce.shop.payment.paypal.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "payment.paypal")
public class PaypalProperties {
    private String mode;
    private String account;
    private Client client;

    @Data
    public static class Client {
        private String id;
        private String secret;

    }
}
