package al.ecommerce.shop.product.controller;

import al.ecommerce.shop.product.model.Product;
import al.ecommerce.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value={"/"})
    public ResponseEntity<?> getProducts() {
        return productService.getProducts();
    }

    @PostMapping(value={"/create"})
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        return productService.createProducts(product);
    }

    @DeleteMapping(value={"/delete"})
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        return productService.deleteProducts(id);
    }

    @PutMapping(value={"/update/{id}"})
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("id") String id) {
        return productService.updateProducts(product, id);
    }
}
