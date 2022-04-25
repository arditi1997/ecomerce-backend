package al.ecommerce.shop.payment.paypal.service;

import al.ecommerce.shop.payment.paypal.model.Order;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PaypalService {
     Payment createPayment(Order order, String cancelUrl, String successUrl) throws PayPalRESTException;

     Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
