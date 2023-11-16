package elearning.app.payments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import elearning.app.model.ProductInfo;
import elearning.app.model.Transactions;
import elearning.app.model.User;
import elearning.app.repository.ProductInfoRepository;
import elearning.app.repository.TransactionsRepository;
import elearning.app.service.UserService;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void handleWebhookEvent(@RequestBody String payload) {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("payload");
        System.out.println(payload);
        try {
            JsonNode jsonNode = objectMapper.readTree(payload);
            String eventType = jsonNode.path("type").asText();

            if ("checkout.session.completed".equals(eventType)) {
                String transactionId = jsonNode.path("data").path("object").path("id").asText();
                Double amount = jsonNode.path("data").path("object").path("amount").asDouble();
                String currency = jsonNode.path("data").path("object").path("currency").asText();
                Long userId = jsonNode.path("data").path("object").path("metadata").path("userId").asLong();

                User user = userService.getUser(userId);
                Transactions transaction = new Transactions();
                transaction.setSessionId(transactionId);
                transaction.setAmount(amount);
                transaction.setCurrency(currency);
                transaction.setUser(user);

                String productIdsString = jsonNode.path("data").path("object").path("metadata").path("productIds").asText();
                String countsString = jsonNode.path("data").path("object").path("metadata").path("counts").asText();

                List<Long> productIds = objectMapper.readValue(productIdsString, new TypeReference<List<Long>>() {
                });
                List<Integer> counts = objectMapper.readValue(countsString, new TypeReference<List<Integer>>() {
                });

                System.out.println("Product IDs: " + productIds);
                System.out.println("Counts: " + counts);

                List<ProductInfo> productInfoList = new ArrayList<>();

                for (int i = 0; i < productIds.size(); i++) {
                    ProductInfo productInfo = new ProductInfo();
                    productInfo.setProduct_id(productIds.get(i));
                    productInfo.setCount(counts.get(i));
                    productInfoList.add(productInfo);
                }

                productInfoRepository.saveAll(productInfoList);

                transaction.setProduct(productInfoList);

                transactionsRepository.save(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}