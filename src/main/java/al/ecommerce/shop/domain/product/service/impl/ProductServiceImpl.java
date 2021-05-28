package al.ecommerce.shop.domain.product.service.impl;

import al.ecommerce.shop.domain.product.model.Product;
import al.ecommerce.shop.domain.product.repository.ProductRepository;
import al.ecommerce.shop.domain.product.service.ProductService;
import al.ecommerce.shop.domain.product.service.mapper.ProductMapper;
import al.ecommerce.shop.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ResponseEntity<?> getProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtos = productMapper.modelToCategoryProductDtoList(productList);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createProducts(Product product) {
        productRepository.saveAndFlush(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteProducts(String id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateProducts(Product product, String id) {
        Product productUpdated = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productUpdated.setPrice(product.getPrice());
        productUpdated.setName(product.getName());
        productUpdated.setQuantity(product.getQuantity());
        productUpdated.setUserList(product.getUserList());
        productUpdated.setCategoryList(product.getCategoryList());
        productRepository.save(product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }
}
