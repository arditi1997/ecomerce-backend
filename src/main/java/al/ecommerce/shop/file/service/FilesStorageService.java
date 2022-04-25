package al.ecommerce.shop.file.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import al.ecommerce.shop.file.model.ResponseMessage;
import al.ecommerce.shop.product.model.Product;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

     void init();

     void save(MultipartFile file, Product product);

     Resource load(String filename);

     void deleteAll();

     Stream<Path> loadAll();

     ResponseEntity<ResponseMessage>  upload(MultipartFile file, String code);
}