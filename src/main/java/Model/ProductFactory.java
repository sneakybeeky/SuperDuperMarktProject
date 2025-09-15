package Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductFactory {
    public static Product create(ProductTypes type, String name, LocalDate expiredDate, LocalDate addedToSupply,
                                 BigDecimal price, int quality) {
        return switch (type) {
            case WINE -> new Wine(name, expiredDate, addedToSupply, price, quality);
            case CHEESE -> new Cheese(name, expiredDate, addedToSupply, price, quality);
            case BREAD -> new Bread(name, expiredDate, addedToSupply, price, quality);
            case TRIVIAL -> new Product(name, expiredDate, addedToSupply, price, quality);
        };
    }
}
