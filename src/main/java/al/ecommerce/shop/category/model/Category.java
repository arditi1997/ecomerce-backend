package al.ecommerce.shop.category.model;

import al.ecommerce.shop.product.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}
