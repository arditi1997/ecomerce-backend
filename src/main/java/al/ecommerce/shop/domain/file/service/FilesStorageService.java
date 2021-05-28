package al.ecommerce.shop.domain.file.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import al.ecommerce.shop.domain.product.model.Product;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

    public void init();

    public void save(MultipartFile file, Product product);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}