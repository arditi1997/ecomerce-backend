package al.ecommerce.shop;
import al.ecommerce.shop.role.model.Role;
import al.ecommerce.shop.role.repository.RoleRepository;
import al.ecommerce.shop.user.model.User;
import al.ecommerce.shop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
public class ShopApplication  implements CommandLineRunner{

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<User> userFinded = userService.getUserByEmail("arditmete2@gmail.com");
        if(userFinded.isEmpty()){
            User user = new User();
            Optional<Role> role = roleRepository.findByName("ADMIN");
            user.setEmail("arditmete2@gmail.com");
            user.setFirstname("ardit");
            user.setUsername("ardit1997");
            user.setLastname("mete");
            user.setPassword("");
            user.setPhoneNumber("0696192872");
            user.setRole(role.get());
            user.setGender("male");
            userService.createUser(user);
        }
    }
}
