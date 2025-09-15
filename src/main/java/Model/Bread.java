package Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Bread extends Product {

    public Bread(String name, LocalDate expiredDate, LocalDate addedToSupply, BigDecimal price, int quality) {
        super(name, expiredDate, addedToSupply, price, quality);
    }

    @Override
    public int calculateQuality(LocalDate dateToCalculate) {
        return quality - 5*(int)(ChronoUnit.DAYS.between(addedToSupply, dateToCalculate));
    }

    @Override
    public BigDecimal calculatePrice(LocalDate dateToCalculate) {
        if ((ChronoUnit.DAYS.between(addedToSupply, dateToCalculate)) >= 3) {
            return BigDecimal.valueOf(1.5);
        } else {
            return super.calculatePrice(dateToCalculate);
        }
    }
}
