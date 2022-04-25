package al.ecommerce.shop.product.service;

import al.ecommerce.shop.product.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ProductService {
    ResponseEntity<?> getProducts();

    ResponseEntity<?> createProducts(Product products);

    ResponseEntity<?> deleteProducts(String id);

    ResponseEntity<?> updateProducts(Product product, String id);

    Optional<Product> findByCode(String code);

}
