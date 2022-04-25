package al.ecommerce.shop.payment.braintree.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BrainTreePayment {
    private String chargeAmount = "";
    private String nonce = "";
    private BillingAddress billingAddress = new BillingAddress();
    private Customer customer = new Customer();
    private CreditCard creditCard = new CreditCard();
    private Product product = new Product();
    private String orderId = "";
    private ShippingAddress shippingAddress = new ShippingAddress();

    @Data
    public static class BillingAddress {
        private String countryName = "";
        private String firstName= "";
        private String lastName= "";
        private String postalCode= "";
        private String streetAddress= "";
    }

    @Data
    public static class Customer {
        private String customerId= "";
        private String firstName= "";
        private String lastName= "";
        private String email= "";
    }

    @Data
    public static class CreditCard {
        private String cardholderName= "";
        private String cvv= "";
        private String expirationDate= "";
        private String expirationYear= "";
        private String expirationMonth= "";
        private String number= "";

    }

    @Data
    public class Product {
        private String productCode= "";
        private String description= "";
        private String name= "";
        private BigDecimal quantity;
        private BigDecimal totalAmount;
        private BigDecimal taxAmount;
    }

    @Data
    public class ShippingAddress {
        private String streetAddress= "";
        private String postalCode= "";
        private String firstName= "";
        private String lastName= "";
        private String countryName= "";
        private String region= "";
    }
}
