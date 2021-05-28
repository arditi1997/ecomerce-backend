package al.ecommerce.shop.domain.file.service.dto;

import al.ecommerce.shop.domain.product.model.Product;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class FileDto {
    private Product product;
    private MultipartFile file;
}
