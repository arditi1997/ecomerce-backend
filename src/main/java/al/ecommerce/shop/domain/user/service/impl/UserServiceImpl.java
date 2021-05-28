package al.ecommerce.shop.domain.user.service.impl;

import al.ecommerce.shop.domain.user.model.User;
import al.ecommerce.shop.domain.user.repository.UserRepository;
import al.ecommerce.shop.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<?> createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUser(String id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>("User is deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateUser(User user, String id) {
        User userUpdated = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userUpdated.setAge(user.getAge());
        userUpdated.setEmail(user.getEmail());
        userUpdated.setGender(user.getGender());
        userUpdated.setLastname(user.getLastname());
        userUpdated.setUsername(user.getUsername());
        userUpdated.setFirstname(user.getFirstname());
        userRepository.save(user);
        return new ResponseEntity<>("User is updated successfully", HttpStatus.OK);
    }
}
