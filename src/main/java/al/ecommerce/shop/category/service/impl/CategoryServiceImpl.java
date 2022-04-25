package al.ecommerce.shop.category.service.impl;

import al.ecommerce.shop.category.model.Category;
import al.ecommerce.shop.category.repository.CategoryRepository;
import al.ecommerce.shop.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public ResponseEntity<?> createCategory(Category category) {
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteCategory(String id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>("Category is deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateCategory(Category category, String id) {
        Category productUpdated = categoryRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Category with id =" + id + " not found"));
        productUpdated.setName(category.getName());
        categoryRepository.save(category);
        return new ResponseEntity<>("Category is updated successfully", HttpStatus.OK);
    }
}
