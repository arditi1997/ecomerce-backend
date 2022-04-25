package al.ecommerce.shop.user.model;

import al.ecommerce.shop.product.model.Product;
import al.ecommerce.shop.role.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String gender;
    private int age;
    private String phoneNumber;
    private String token;

    @OneToOne
    @JoinColumn
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Product> product;

}
