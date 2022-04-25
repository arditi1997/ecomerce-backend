package al.ecommerce.shop.payment.braintree.controller;

import al.ecommerce.shop.payment.braintree.model.BrainTreePayment;
import al.ecommerce.shop.payment.braintree.service.BrainTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brainTree")
public class BrainTreeController {

    @Autowired
    private BrainTreeService brainTreeService;

    @RequestMapping(value = "/payment/process", method = RequestMethod.POST)
    public ResponseEntity<Object> processTransaction(@RequestBody BrainTreePayment brainTreePayment) {

      return brainTreeService.processTransaction(brainTreePayment);
    }
}