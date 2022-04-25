package al.ecommerce.shop.product.service.dto;

import al.ecommerce.shop.category.model.Category;
import lombok.Data;

@Data
public class ProductDto {

    private String productId;
    private String productName;
    private String productPrice;
    private String productCode;
    private String productQuantity;
    private Category category;
}
