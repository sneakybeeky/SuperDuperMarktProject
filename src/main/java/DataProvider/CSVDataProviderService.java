package DataProvider;

import Model.Product;
import Model.ProductFactory;
import Model.ProductTypes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class CSVDataProviderService implements DataProviderService{

    private final String limiter;
    private final String fileToRead;

    public CSVDataProviderService(String limiter, String fileToRead) {
        this.limiter = limiter;
        this.fileToRead = fileToRead;
    }

    public List<Product> provideData() {
        return readFile();
    }

    private List<Product> readFile() {
        List<Product> supply = new LinkedList<>();
        try {
            BufferedReader readProductsFromFile = new BufferedReader(new FileReader(fileToRead));

            supply = readProductsFromFile.lines()
                    .map(n -> createProductFromLine(n.split(limiter)))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error while reading the file");
        }
        return supply;
    }

    private static Optional<Product> createProductFromLine(String[] values) {
        if(values.length != 6) {
            System.out.println("Invalid product data");
        } else {
            for(ProductTypes productType : ProductTypes.values()) {
                if (productType.name().equals(values[0])) {
                    return Optional.of(ProductFactory.create(productType,
                            values[1],
                            LocalDate.parse(values[2]),
                            LocalDate.parse(values[3]),
                            new BigDecimal(values[4]),
                            parseInt(values[5])));
                }
            }
        }
        return Optional.empty();
    }
}
