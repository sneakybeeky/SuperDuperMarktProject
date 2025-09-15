import Model.Product;
import Model.ProductFactory;
import Model.ProductTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private static Product testProduct;
    private static Product testCheese;
    private static Product testWhine;
    private static Product testBread;

    @BeforeAll
    public static void initProduct() {
        testProduct = ProductFactory.create(ProductTypes.TRIVIAL, "TestProd", LocalDate.now().plusDays(40), LocalDate.now(), new BigDecimal("5.99"), 40);
        testWhine = ProductFactory.create(ProductTypes.WINE, "TestWhine",LocalDate.now().plusDays(40),  LocalDate.now(), new BigDecimal("14.5"), 30);
        testCheese = ProductFactory.create(ProductTypes.CHEESE, "TestCheese", LocalDate.now().plusDays(40),  LocalDate.now(), new BigDecimal("7.99"), 60);
        testBread = ProductFactory.create(ProductTypes.BREAD, "TestBread", LocalDate.now().plusDays(40),  LocalDate.now(), new BigDecimal("6.5"), 40);
    }

    @Test
    void cheeseLosesQualityDaily() {
        assertEquals(testCheese.calculateQuality(testCheese.getAddedToSupply()) -1, testCheese.calculateQuality(testCheese.getAddedToSupply().plusDays(1)));
        assertEquals(testCheese.calculateQuality(testCheese.getAddedToSupply()) -5, testCheese.calculateQuality(testCheese.getAddedToSupply().plusDays(5)));
    }

    @Test
    void cheeseIsRemovedIfQualityLowerThirty() {
        LocalDate dateOverQualityThreshold = testCheese.getAddedToSupply().plusDays(30);
        assertTrue(testCheese.calculateStatus(dateOverQualityThreshold));
        LocalDate dateUnderQualityThreshold = testCheese.getAddedToSupply().plusDays(31);
        assertFalse(testCheese.calculateStatus(dateUnderQualityThreshold));
    }

    @Test
    void cheesePriceIsCheckedDaily() {
        assertNotEquals(testCheese.calculatePrice(testCheese.getAddedToSupply()), testCheese.calculatePrice(testCheese.getAddedToSupply().plusDays(3)));
    }

    @Test
    void checkWhinePriceStaysTheSame() {
        assertEquals(testWhine.calculatePrice(testCheese.getAddedToSupply()), testWhine.calculatePrice(testCheese.getAddedToSupply().plusDays(20)));
    }

    @Test
    void whineDoesNotExpire() {
        assertTrue(testWhine.calculateStatus(testWhine.getAddedToSupply().plusDays(5000)));
    }

    @Test
    void whineDoesGainQuality() {
        assertTrue(testWhine.getQuality() < testWhine.calculateQuality(testWhine.getAddedToSupply().plusDays(20)));
        assertEquals(testWhine.getQuality() + 2, testWhine.calculateQuality(testWhine.getAddedToSupply().plusDays(20)));
    }

    @Test
    void whinePriceIsCappedAtFifty() {
        assertEquals(50, testWhine.calculateQuality(testWhine.getAddedToSupply().plusDays(50000)));
    }

    @Test
    void breadLosesFiveQualityPerDay() {
        assertEquals(35, testBread.calculateQuality(testBread.getAddedToSupply().plusDays(1)));
        assertEquals(30, testBread.calculateQuality(testBread.getAddedToSupply().plusDays(2)));
    }

    @Test
    void dailyPriceIsAffectedByQuality() {
        assertEquals(testProduct.calculatePrice(testProduct.getAddedToSupply()), testProduct.calculatePrice(testProduct.getAddedToSupply()).max(testProduct.calculatePrice(testProduct.getAddedToSupply().plusDays(5))));
    }

    @Test
    void breadIsFixedPriceAfterThreeDays() {
        assertNotEquals(BigDecimal.valueOf(1.5), testBread.calculatePrice(testBread.getAddedToSupply().plusDays(2)));
        assertEquals(BigDecimal.valueOf(1.5), testBread.calculatePrice(testBread.getAddedToSupply().plusDays(3)));
        assertEquals(BigDecimal.valueOf(1.5), testBread.calculatePrice(testBread.getAddedToSupply().plusDays(4)));
    }
}
