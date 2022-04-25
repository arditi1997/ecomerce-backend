package al.ecommerce.shop.product.service.mapper;

import al.ecommerce.shop.product.model.Product;
import al.ecommerce.shop.product.service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    public ProductDto modelToCategoryProductDto(Product product){
        ProductDto dto = new ProductDto();

        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setProductPrice(product.getPrice());
        dto.setProductCode(product.getCode());
        dto.setCategory(product.getCategory());
        dto.setProductQuantity(product.getQuantity());
        return dto;
    }

    public List<ProductDto> modelToCategoryProductDtoList(List<Product> productList){
        List<ProductDto> dtos = new ArrayList<>();

        productList.forEach(product -> {
            dtos.add(modelToCategoryProductDto(product));
        });

        return dtos;
    }

}
