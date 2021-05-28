package al.ecommerce.shop.domain.product.model;

import al.ecommerce.shop.domain.category.model.Category;
import al.ecommerce.shop.domain.file.model.File;
import al.ecommerce.shop.domain.file.model.FileInfo;
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
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;
    private int quantity;
    private String code;
    private double price;

    @ManyToMany
    @JoinTable(
            name = "user_product",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )
    )
    private List<User> userList;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id"
            )
    )
    private List<Category> categoryList;


    @OneToMany(mappedBy = "product")
    private List<File> files;

    public void addFile(File file){
        files.add(file);
        file.setProduct(this);
    }
}
