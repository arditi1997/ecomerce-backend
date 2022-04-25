package al.ecommerce.shop.errorHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INCORRECT_REQUEST_BODY(5400, Constants.INCORRECT_REQUEST_BODY_MSG),
    INTERNAL_ERROR_SERVER_HAS_NO_DATA(5500, Constants.INTERNAL_ERROR_SERVER_HAS_NO_DATA_MSG),
    USER_NOT_FOUND(5422, Constants.USER_NOT_FOUND),
    PRODUCT_NOT_FOUND(5422, Constants.PRODUCT_NOT_FOUND),
    CATEGORY_NOT_FOUND(5422, Constants.CATEGORY_NOT_FOUND);

    private final int code;
    private final String msg;

    public static class Constants {
        public final static String USER_NOT_FOUND = "User not found!";
        public final static String PRODUCT_NOT_FOUND = "Product not found!";
        public final static String CATEGORY_NOT_FOUND = "Category not found!";
        public final static String INCORRECT_REQUEST_BODY_MSG = "Please check again your request body!";
        public final static String INTERNAL_ERROR_SERVER_HAS_NO_DATA_MSG = "Problem with the server. Please contact an administrator!";
    }
}