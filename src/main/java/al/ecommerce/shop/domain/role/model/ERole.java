package al.ecommerce.shop.domain.role.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
    public enum ERole {
        USER("USER"),
        ADMIN("ADMIN");

        private final String name;
}
