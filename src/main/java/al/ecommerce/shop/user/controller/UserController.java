package al.ecommerce.shop.user.controller;

import al.ecommerce.shop.user.model.User;
import al.ecommerce.shop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping(value = {"/"})
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(value = {"/create"})
    public ResponseEntity<?> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete/{/id}"})
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {

        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @PutMapping(value = {"/update/{/id}"})
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") String id) {
        return new ResponseEntity<>("User is updated successfully", HttpStatus.OK);
    }
}
