package al.ecommerce.shop.domain.file.model;

import al.ecommerce.shop.domain.product.model.Product;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table
@Entity(name = "File")
@Data
public class File {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn
    private Product product;

}
