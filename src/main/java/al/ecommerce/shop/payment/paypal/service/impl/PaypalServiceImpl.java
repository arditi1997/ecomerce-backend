package al.ecommerce.shop.payment.paypal.service.impl;

import al.ecommerce.shop.payment.paypal.service.PaypalService;
import al.ecommerce.shop.payment.paypal.model.Order;
import al.ecommerce.shop.product.service.dto.ProductDto;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalServiceImpl implements PaypalService {

    @Autowired
    private APIContext apiContext;

    @Override
    public Payment createPayment(Order order, String cancelUrl, String successUrl) throws PayPalRESTException{
        Amount amount = new Amount();
        amount.setCurrency(order.getCurrency());
        order.setPrice(new BigDecimal(order.getPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue());
        amount.setTotal(String.format("%.2f", order.getPrice()));

        List<Item> items = new ArrayList<>();
        for(ProductDto productdto: order.getProducts()){
            Item item = new Item();
            item.setCurrency(order.getCurrency());
            item.setCategory(productdto.getCategory().getName());
            item.setPrice(productdto.getProductPrice());
            item.setName(productdto.getProductName());
            item.setQuantity(productdto.getProductQuantity());
            items.add(item);
        }
        ItemList itemList = new ItemList();
        itemList.setItems(items);

        Transaction transaction = new Transaction();
        transaction.setDescription(order.getDescription());
        transaction.setAmount(amount);
        transaction.setItemList(itemList);

        List transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(order.getMethod());

        Payment payment = new Payment();
        payment.setIntent(order.getIntent());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }
    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
}
