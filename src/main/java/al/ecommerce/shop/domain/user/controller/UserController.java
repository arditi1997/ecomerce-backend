package al.ecommerce.shop.domain.user.controller;

import al.ecommerce.shop.auth.JwtTokenUtil;
import al.ecommerce.shop.domain.user.model.User;
import al.ecommerce.shop.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value={"/user/users", "/admin/users"})
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(value={"/user/create-user", "/admin/create-user"})
    public ResponseEntity<?> register(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping(value={"/user/delete-user/{id}", "/admin/delete-user/{/id}"})
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        return userService.deleteUser(id);
    }

    @PutMapping(value={"/user/update-user/{id}", "/admin/update-user/{/id}"})
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") String id) {
        return userService.updateUser(user, id);
    }

}
