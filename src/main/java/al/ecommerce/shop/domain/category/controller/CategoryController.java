package al.ecommerce.shop.domain.category.controller;

import al.ecommerce.shop.auth.JwtTokenUtil;
import al.ecommerce.shop.domain.category.model.Category;
import al.ecommerce.shop.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService productService;

    @GetMapping(value={"/user/categories", "/admin/categories"})
    public List<Category> getCategories( @RequestHeader(JwtTokenUtil.TOKEN_HEADER) String jwtToken) {
        return productService.getCategories();
    }

    @PostMapping(value={"/user/create-category", "/admin/create-category"})
    public ResponseEntity<?> createCategory(@RequestBody Category category,  @RequestHeader(JwtTokenUtil.TOKEN_HEADER) String jwtToken) {
        return productService.createCategory(category);
    }

    @DeleteMapping(value={"/user/delete-category/{id}", "/admin/delete-category/{id}"})
    public ResponseEntity<?> deleteCategory(@PathVariable("id") String id) {
        return productService.deleteCategory(id);
    }

    @PutMapping(value={"/user/update-category/{id}", "/admin/update-category/{id}"})
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable("id") String id) {
        return productService.updateCategory(category, id);
    }

}
