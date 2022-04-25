package al.ecommerce.shop.payment.braintree.service;

import al.ecommerce.shop.payment.braintree.model.BrainTreePayment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface BrainTreeService {
    ResponseEntity<Object> processTransaction(@RequestBody BrainTreePayment brainTreePayment);

}
