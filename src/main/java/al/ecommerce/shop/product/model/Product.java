package al.ecommerce.shop.product.model;

import al.ecommerce.shop.category.model.Category;
import al.ecommerce.shop.file.model.File;
import al.ecommerce.shop.user.model.User;
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
    private String quantity;
    private String code;
    private String price;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Category category;


    @OneToMany(mappedBy = "product")
    private List<File> files;

    public void addFile(File file){
        files.add(file);
        file.setProduct(this);
    }
}
