package al.ecommerce.shop.file.service.impl;

import al.ecommerce.shop.errorHandler.CustomException;
import al.ecommerce.shop.errorHandler.ErrorCode;
import al.ecommerce.shop.file.controller.FilesController;
import al.ecommerce.shop.file.model.File;
import al.ecommerce.shop.file.model.ResponseMessage;
import al.ecommerce.shop.file.repository.FileRepository;
import al.ecommerce.shop.file.service.FilesStorageService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import al.ecommerce.shop.product.model.Product;
import al.ecommerce.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class FilesStorageServiceImpl implements FilesStorageService {

    private final Path root = Paths.get("uploads");
    private final FileRepository fileRepository;
    private final ProductService productService;

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

    @Override
    public ResponseEntity<ResponseMessage> upload(MultipartFile file, String code) {
        Product product = productService.findByCode(code).orElseThrow(()-> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        String message = "";
        try {
            save(file, product);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
