package al.ecommerce.shop.domain.category.model;

import al.ecommerce.shop.domain.product.model.Product;
import al.ecommerce.shop.domain.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Category {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "categoryList")
    private List<Product> productList;
}
