package DataProvider;

import Model.Product;

import java.util.List;
import java.util.Scanner;

public interface DataProviderService {

    String DB_URL = "jdbc:mysql://localhost:3306/SuperDuperMarkt";
    String USER = "root";
    String PASSWORD = "123";

    String DELIMITER = ";";
    String CSV_FILE = "src/main/resources/productList.csv";

    List<Product> provideData();

    static DataProviderService getDataSourceFromUserInput() {
        Scanner s = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Sollen die Daten aus der (1)CSV Datei oder aus der (2)Datenbank gelesen werden?");
            input = s.nextLine();
            if (input.equals("1")) return new CSVDataProviderService(DELIMITER, CSV_FILE) ;
            if (input.equals("2")) return new SQLDataProviderService(DB_URL, USER, PASSWORD);
            System.out.println("Ungültige Eingabe.");
        }
    }
}
