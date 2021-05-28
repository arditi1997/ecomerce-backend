package al.ecommerce.shop.domain.user.model;

import al.ecommerce.shop.domain.product.model.Product;
import al.ecommerce.shop.domain.role.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.AbstractAuditable;

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
    private long phoneNumber;
    private String token;

    @OneToOne
    @JoinColumn
    private Role role;

    @JsonIgnore
    @ManyToMany(mappedBy = "userList")
    private List<Product> productList;

}
