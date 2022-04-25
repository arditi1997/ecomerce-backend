package al.ecommerce.shop.file.service.dto;

import al.ecommerce.shop.product.model.Product;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class FileDto {
    private Product product;
    private MultipartFile file;
}
