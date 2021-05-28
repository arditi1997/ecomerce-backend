package al.ecommerce.shop.domain.product.controller;

import al.ecommerce.shop.domain.product.model.Product;
import al.ecommerce.shop.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value={"/user/product-list", "/admin/product-list"})
    public ResponseEntity<?> getProducts() {
        return productService.getProducts();
    }

    @PostMapping(value={"/user/create-product" , "/admin/create-product"})
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        return productService.createProducts(product);
    }

    @DeleteMapping(value={"/user/delete-product/{id}", "/admin/delete-product"})
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        return productService.deleteProducts(id);
    }


    @PutMapping(value={"/user/update-product/{id}", "/admin/update-product/{id}"})
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("id") String id) {
        return productService.updateProducts(product, id);
    }
}
