package al.ecommerce.shop.product.service.impl;

import al.ecommerce.shop.errorHandler.CustomException;
import al.ecommerce.shop.errorHandler.ErrorCode;
import al.ecommerce.shop.product.model.Product;
import al.ecommerce.shop.product.repository.ProductRepository;
import al.ecommerce.shop.product.service.ProductService;
import al.ecommerce.shop.product.service.mapper.ProductMapper;
import al.ecommerce.shop.product.service.dto.ProductDto;
import al.ecommerce.shop.user.model.User;
import al.ecommerce.shop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> getProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtos = productMapper.modelToCategoryProductDtoList(productList);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createProducts(Product product) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =  userRepository.findByUsername(name).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        product.setUser(user);
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
        Product productUpdated = productRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =  userRepository.findByUsername(name).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        product.setUser(user);
        productUpdated.setPrice(product.getPrice());
        productUpdated.setName(product.getName());
        productUpdated.setQuantity(product.getQuantity());
        productUpdated.setCategory(product.getCategory());
        productRepository.save(product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    @Override
    public Optional<Product> findByCode(String code) {
        productRepository.flush();
        return Optional.ofNullable(productRepository.findByCode(code).orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND)));
    }
}
