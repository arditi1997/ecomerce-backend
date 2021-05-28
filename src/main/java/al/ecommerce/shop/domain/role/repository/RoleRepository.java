package al.ecommerce.shop.domain.role.repository;

import al.ecommerce.shop.domain.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
        }
