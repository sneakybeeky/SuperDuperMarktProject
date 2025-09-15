package Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Product {
    protected final String name;
    protected final LocalDate expiredDate;
    protected final LocalDate addedToSupply;
    protected final BigDecimal price;
    protected int quality;

    protected Product(String name, LocalDate expiredDate, LocalDate addedToSupply, BigDecimal price, int quality) {
        this.name = name;
        this.expiredDate = expiredDate;
        this.addedToSupply = addedToSupply;
        this.price = price;
        this.quality = quality;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getAddedToSupply() {
        return addedToSupply;
    }

    //DEFAULT PRODUCT BEHAVIOR
    public BigDecimal calculatePrice(LocalDate dateToCalculate) {
        return price.add(BigDecimal.valueOf(.1 * calculateQuality(dateToCalculate))).setScale(2, RoundingMode.HALF_UP);
    }

    public int calculateQuality(LocalDate dateToCalculate) {
        return quality - (int)(ChronoUnit.DAYS.between(addedToSupply, dateToCalculate));
    }

    public boolean calculateStatus(LocalDate dateToCalculate) {
        if (calculateQuality(dateToCalculate) < 0 || expiredDate.isBefore(dateToCalculate)) {
            return false;
        }
        return true;
    }
}
