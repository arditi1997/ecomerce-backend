package al.ecommerce.shop.domain.role.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
