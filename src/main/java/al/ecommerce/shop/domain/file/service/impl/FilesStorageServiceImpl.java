package al.ecommerce.shop.domain.file.service.impl;

import al.ecommerce.shop.domain.file.controller.FilesController;
import al.ecommerce.shop.domain.file.model.File;
import al.ecommerce.shop.domain.file.repository.FileRepository;
import al.ecommerce.shop.domain.file.service.FilesStorageService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import al.ecommerce.shop.domain.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class FilesStorageServiceImpl implements FilesStorageService {

    private final Path root = Paths.get("uploads");
    private final FileRepository fileRepository;

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file, Product product) {
        File fileProduct = new File();
        Path path = root.resolve(file.getOriginalFilename());
        String url = MvcUriComponentsBuilder
                .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
        try {
//            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            fileProduct.setFilename(file.getOriginalFilename());
            fileProduct.setUrl(url);
            fileProduct.setProduct(product);
            fileRepository.save(fileProduct);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
