package al.ecommerce.shop.user.service;

import al.ecommerce.shop.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();

    User createUser(User user);

    String deleteUser(String id);

     Optional<User> getUserByEmail(String email);

    void updateUser(User user, String id);

}
