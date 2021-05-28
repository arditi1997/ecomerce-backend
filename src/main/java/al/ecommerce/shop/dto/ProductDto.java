package al.ecommerce.shop.dto;

import al.ecommerce.shop.domain.category.model.Category;
import lombok.Data;
import java.util.List;

@Data
public class ProductDto {

    private String productId;
    private String productName;
    private double productPrice;
    private String productCode;
    private List<Category> categoryList;
}
