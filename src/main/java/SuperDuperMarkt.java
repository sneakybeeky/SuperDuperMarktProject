import Model.Product;
import java.util.List;

import static DataProvider.DataProviderService.getDataSourceFromUserInput;

public class SuperDuperMarkt {

    public static void main(String[] args) {
        List<Product> supply = getDataSourceFromUserInput().provideData();
        SupplyConsolePrinter.printStartSupply(supply);
        SupplyConsolePrinter.simulateTimeFrame(supply, 22);
    }
}
