package elearning.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import elearning.demo.dto.user.UserCreatedRequest;
import elearning.demo.dto.user.UserLoginRequest;
import elearning.demo.dto.user.UserUpadateProducts;
import elearning.demo.dto.user.UserUpdateRequest;
import elearning.demo.mapper.UserMapper;
import elearning.demo.models.Items;
import elearning.demo.models.Product;
import elearning.demo.models.Products;
import elearning.demo.models.User;
import elearning.demo.repository.UserRepository;
import elearning.demo.security.JwtService;
import elearning.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserCreatedRequest saveUser(UserCreatedRequest appUser) throws Exception {
        User usr = repository.findByEmail(appUser.getUsername());
        if (usr != null) {
            throw new Exception("User with this username already exists.");
        }
        User user = new User(appUser.getUsername(), appUser.getEmail(), appUser.getLocation());
        user.setPassword(passwordEncoder.encode(appUser.getPassword()));
        user.setRole("USER_ROLE");
        var returnUser = repository.save(user);
        return new UserCreatedRequest(returnUser.getUsername(), returnUser.getEmail(), returnUser.getLocation());
    }

    @Override
    public String login(UserLoginRequest authRequest) throws Exception {
        org.springframework.security.core.Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            User user = repository.findByUsername(authRequest.getUsername());
            Long userId = user.getId();
            return jwtService.generateToken(authRequest.getUsername(), userId);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @Override
    public UserUpdateRequest updateUser(Long userId, UserUpdateRequest updatedUserData) throws Exception {
        User existingUser = repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Korisnik s ID-om " + userId + " nije pronađen."));

        existingUser.setUsername(updatedUserData.getUsername());
        existingUser.setEmail(updatedUserData.getEmail());
        existingUser.setPassword(updatedUserData.getPassword());
        existingUser.setLocation(updatedUserData.getLocation());

        User updatedUser = repository.save(existingUser);

        return new UserUpdateRequest(updatedUser.getUsername(), updatedUser.getEmail(), updatedUser.getPassword(),
                updatedUser.getLocation());

    }

    @Override
    public UserCreatedRequest getUser(Long userId) throws Exception {
        UserCreatedRequest user = userMapper.entityToDto(repository.getById(userId));
        return user;

    }

    @Override
    public User updateUserProduct(Long userId, UserUpadateProducts updatedUserData) throws Exception {
        boolean areAllNamesEqual = false;
        String name = "";
        for (int i = 1; i < updatedUserData.getPlaces().size(); i++) {
            if (updatedUserData.getPlaces().get(i) != updatedUserData.getPlaces().get(0)) {
                areAllNamesEqual = true;
                name = updatedUserData.getPlaces().get(i);
            }
        }
        Items item = new Items();
        List<Items> items = new ArrayList();
        List<Products> products = new ArrayList();
        User existingUser = repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Korisnik s ID-om " + userId + " nije pronađen."));

        if (areAllNamesEqual) {
            for (int i = 0; i < updatedUserData.getProductIds().size(); i++) {
                Product product = new Product(updatedUserData.getProductIds().get(i));
                item.setProductId(product);
                item.setCount(updatedUserData.getCounts().get(i));
            }

            items.add(item);
            Products product = new Products(items, updatedUserData.getDate(), updatedUserData.getStatus(), updatedUserData.getPrice(),
                    name);

            products.add(product);
            existingUser.setProducts(products);
        } else {
            for (int i = 0; i < updatedUserData.getProductIds().size(); i++) {
                Product product = new Product(updatedUserData.getProductIds().get(i));
                item.setProductId(product);
                item.setCount(updatedUserData.getCounts().get(i));
                items.add(item);

                Products productt = new Products(items, updatedUserData.getDate(), updatedUserData.getStatus(), updatedUserData.getPrice(),
                        name);

                products.add(productt);
                existingUser.setProducts(products);
            }

        }

        return existingUser;

    }

    @Override
    public String getStatus(Long userId, String itemId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Korisnik s ID-om " + userId + " nije pronađen."));

        String itemStatus = null;

        for (Products products : user.getProducts()) {
            if (products.getId().equals(itemId)) {
                itemStatus = products.getStatus();
                break;
            }
        }

        if (itemStatus == null) {
            return "Stavka nije pronađena";
        }

        return itemStatus;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void updateStatus() {
        List<User> users = repository.findAll();

        for (User user : users) {
            for (Products products : user.getProducts()) {
                Date currentDate = new Date();
                Date purchaseDate = products.getDate();
                long twoDaysInMillis = 2 * 24 * 60 * 60 * 1000;
                Date nextStatusUpdateDate = new Date(purchaseDate.getTime() + twoDaysInMillis);

                String[] statusOptions = { "Narudžba primljena", "Narudžba poslata", "Narudžba u tranzitu", "Narudžba stigla na odredište",
                        "Narudžba u procesu dostave", "Narudžba isporučena" };

                if (currentDate.after(nextStatusUpdateDate)) {
                    int currentStatusIndex = Arrays.asList(statusOptions).indexOf(products.getStatus());

                    if (currentStatusIndex < statusOptions.length - 1) {
                        String newStatus = statusOptions[currentStatusIndex + 1];
                        products.setStatus(newStatus);
                    }
                }
            }

            repository.save(user);
        }
    }
}
