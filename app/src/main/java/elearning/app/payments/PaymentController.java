package elearning.app.payments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import elearning.app.model.Product;
import elearning.app.model.ProductInfo;
import elearning.app.model.Transactions;
import elearning.app.repository.ProductRepository;
import elearning.app.repository.TransactionsRepository;
import elearning.app.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    final JwtUtil jwtUtil;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    ProductRepository productRepository;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostMapping("/create-checkout-session")
    public String createCheckoutSession(@RequestBody PaymentRequest paymentRequest,
            @RequestHeader("Authorization") String authorizationHeader) {
        Stripe.apiKey = stripeApiKey;
        String token = authorizationHeader.substring(7);
        Long userId = jwtUtil.getUserIdFromToken(token);
        try {
            Session session = Session.create(createCheckoutSessionParams(paymentRequest, userId));
            return session.getId();

        } catch (StripeException e) {
            e.printStackTrace();
            return "Error processing payment.";
        }
    }

    private SessionCreateParams createCheckoutSessionParams(PaymentRequest paymentRequest, Long userId) {
        List<ProductInfo> products = paymentRequest.getProducts();

        List<SessionCreateParams.LineItem> lineItems = products.stream()
                .map(productInfo -> createSessionLineItem(productInfo, paymentRequest.getCurrency())).collect(Collectors.toList());

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("productIds", products.stream().map(productInfo -> productInfo.getProductId()).collect(Collectors.toList()));
        metadata.put("counts", products.stream().map(ProductInfo::getCount).collect(Collectors.toList()));
        metadata.put("userId", userId);

        SessionCreateParams.Builder builder = SessionCreateParams.builder().addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl("http://localhost:3000/completedPurchase")
                .setCancelUrl("http://localhost:3000/home").addAllLineItem(lineItems);

        for (Map.Entry<String, Object> entry : metadata.entrySet()) {
            builder.putMetadata(entry.getKey(), entry.getValue().toString());
        }

        return builder.build();
    }

    private SessionCreateParams.LineItem createSessionLineItem(ProductInfo productInfo, String currency) {

        if (productInfo.getProductId() != null) {
            Product product = productRepository.getById(productInfo.getProductId());
            String priceString = product.getPrice();
            priceString = priceString.replaceAll("[^\\d.]", "");
            double price = Double.parseDouble(priceString);
            return SessionCreateParams.LineItem.builder()
                    .setPriceData(SessionCreateParams.LineItem.PriceData.builder().setCurrency(currency)
                            .setProductData(
                                    SessionCreateParams.LineItem.PriceData.ProductData.builder().setName(product.getTitle()).build())
                            .setUnitAmount((long) (price * 100)).build())
                    .setQuantity((long) productInfo.getCount()).build();
        } else {
            return null;
        }
    }

    @GetMapping("/get-transaction/{id}")
    public List<Transactions> getTransaction(@PathVariable Long id) {
        return transactionsRepository.findByUserId(id);
    }

}
