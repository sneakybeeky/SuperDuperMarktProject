package Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cheese extends Product {

    public Cheese(String name, LocalDate expiredDate, LocalDate addedToSupply, BigDecimal price, int quality) {
        super(name, expiredDate, addedToSupply, price, quality);
    }

    @Override
    public boolean calculateStatus(LocalDate dateToCalculate) {
        if (calculateQuality(dateToCalculate) < 30 || dateToCalculate.isAfter(this.getExpiredDate())) {
            return false;
        }
        return true;
    }
}
