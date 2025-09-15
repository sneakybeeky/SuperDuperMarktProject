package Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Wine extends Product {

    protected Wine(String name, LocalDate expiredDate, LocalDate addedToSupply, BigDecimal price, int quality) {
        super(name, expiredDate, addedToSupply, price, quality);
    }

    @Override
    public boolean calculateStatus(LocalDate dateToCalculate) {
        return true;
    }

    @Override
    public int calculateQuality(LocalDate dateToCalculate) {
        return Math.min(quality + (((int)(ChronoUnit.DAYS.between(addedToSupply, dateToCalculate))) / 10), 50);
    }

    @Override
    public BigDecimal calculatePrice(LocalDate dateToCalculate) {
        return price;
    }
}
