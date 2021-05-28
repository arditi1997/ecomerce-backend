package al.ecommerce.shop.domain.file.repository;

import al.ecommerce.shop.domain.file.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, String> {
}
