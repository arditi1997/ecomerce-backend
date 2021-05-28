package al.ecommerce.shop.domain.product.repository;

import al.ecommerce.shop.domain.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    public Product findByCode(String code);
}
