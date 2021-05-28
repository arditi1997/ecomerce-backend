package al.ecommerce.shop.domain.product.service;

import al.ecommerce.shop.domain.product.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface ProductService {
     ResponseEntity<?> getProducts();

    ResponseEntity<?> createProducts(Product products);

    ResponseEntity<?> deleteProducts(String id);

    ResponseEntity<?> updateProducts(Product product, String id);

}
