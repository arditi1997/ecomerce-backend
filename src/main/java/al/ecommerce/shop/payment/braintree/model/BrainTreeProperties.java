package al.ecommerce.shop.payment.braintree.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "payment.braintree")
public class BrainTreeProperties {
    private String merchantId;
    private String publicKey;
    private String privateKey;
    private String mode;
}
