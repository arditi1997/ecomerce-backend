package al.ecommerce.shop.payment.braintree.service.impl;

import al.ecommerce.shop.payment.braintree.controller.BrainTreeController;
import al.ecommerce.shop.payment.braintree.model.BrainTreePayment;
import al.ecommerce.shop.payment.braintree.service.BrainTreeService;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static al.ecommerce.shop.payment.braintree.config.BrainTreeConfig.gateway;

@Service
public class BrainTreeServiceImpl implements BrainTreeService {
    private static final Logger logger = LoggerFactory.getLogger(BrainTreeController.class);

    @Override
    public ResponseEntity<Object> processTransaction(BrainTreePayment brainTreePayment) {

        Transaction transaction = null;
        TransactionRequest request = buildTransactionRequest(brainTreePayment);
        Result<Transaction> result = gateway.transaction().sale(request);
        if (result.isSuccess() || result.getTransaction() != null) {
            try {
                transaction = result.getTarget();
                transaction = gateway.transaction().find(transaction.getId());
            } catch (Exception e) {
                logger.info("Exception {}", e);
            }
            return new ResponseEntity<>(transaction, HttpStatus.OK);

        } else {

            return new ResponseEntity<>("Nonce is allowed to use only once", HttpStatus.OK);
        }
    }

    private TransactionRequest buildTransactionRequest(BrainTreePayment brainTreePayment) {
        TransactionRequest request = new TransactionRequest();
        //billing address
        request.billingAddress()
                //billing address
                .countryName(brainTreePayment.getBillingAddress().getCountryName()).firstName(brainTreePayment.getBillingAddress().getFirstName()).lastName(brainTreePayment.getBillingAddress().getLastName()).postalCode(brainTreePayment.getBillingAddress().getPostalCode()).streetAddress(brainTreePayment.getBillingAddress().getStreetAddress()).done()
                //customer
                .customer().customerId(brainTreePayment.getCustomer().getCustomerId()).firstName(brainTreePayment.getCustomer().getFirstName()).lastName(brainTreePayment.getCustomer().getLastName()).email(brainTreePayment.getCustomer().getEmail()).done()
                //amount
                .amount(new BigDecimal(brainTreePayment.getChargeAmount()))
                //creditcard
                .creditCard().cardholderName(brainTreePayment.getCreditCard().getCardholderName()).cvv(brainTreePayment.getCreditCard().getCvv()).expirationDate(brainTreePayment.getCreditCard().getExpirationDate()).expirationYear(brainTreePayment.getCreditCard().getExpirationYear()).expirationMonth(brainTreePayment.getCreditCard().getExpirationMonth()).number(brainTreePayment.getCreditCard().getNumber()).done()
                //product
                .lineItem().productCode(brainTreePayment.getProduct().getProductCode()).description(brainTreePayment.getProduct().getDescription()).name(brainTreePayment.getProduct().getName()).quantity(brainTreePayment.getProduct().getQuantity()).totalAmount(brainTreePayment.getProduct().getTotalAmount()).taxAmount(brainTreePayment.getProduct().getTaxAmount()).done()
                //order id
                .orderId("").
                //shipping address
                        shippingAddress().streetAddress(brainTreePayment.getShippingAddress().getStreetAddress()).postalCode(brainTreePayment.getShippingAddress().getPostalCode()).lastName(brainTreePayment.getShippingAddress().getLastName()).firstName(brainTreePayment.getShippingAddress().getLastName()).countryName(brainTreePayment.getShippingAddress().getCountryName()).region(brainTreePayment.getShippingAddress().getRegion()).done()

                .paymentMethodNonce(brainTreePayment.getNonce()).options().submitForSettlement(true).done();

        return request;
    }
}
