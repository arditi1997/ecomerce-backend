package al.ecommerce.shop.domain.user.service;

import al.ecommerce.shop.domain.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService {
    List<User> getUsers();

    ResponseEntity<?> createUser(User user);

    ResponseEntity<?> deleteUser(String id);

    ResponseEntity<?> updateUser(User user, String id);

}
