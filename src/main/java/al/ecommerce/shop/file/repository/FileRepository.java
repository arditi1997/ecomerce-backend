package al.ecommerce.shop.file.repository;

import al.ecommerce.shop.file.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, String> {
}
