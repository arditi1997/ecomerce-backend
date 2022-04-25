package al.ecommerce.shop.user.service.impl;

import al.ecommerce.shop.errorHandler.CustomException;
import al.ecommerce.shop.errorHandler.ErrorCode;
import al.ecommerce.shop.role.model.Role;
import al.ecommerce.shop.role.repository.RoleRepository;
import al.ecommerce.shop.user.model.User;
import al.ecommerce.shop.user.repository.UserRepository;
import al.ecommerce.shop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<Role> role = roleRepository.findByName("USER");
        user.setRole(role.get());
        userRepository.save(user);
        return user;
    }

    @Override
    public String deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
        if(user!= null)
            return "User not found";
        else
            userRepository.deleteById(id);
            return "User is deleted successfully";
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
      return userRepository.findByEmail(email);
    }

    @Override
    public void updateUser(User user, String id) {
        User userUpdated = userRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
        userUpdated.setAge(user.getAge());
        userUpdated.setEmail(user.getEmail());
        userUpdated.setGender(user.getGender());
        userUpdated.setLastname(user.getLastname());
        userUpdated.setUsername(user.getUsername());
        userUpdated.setFirstname(user.getFirstname());
        userRepository.save(user);
    }
}
