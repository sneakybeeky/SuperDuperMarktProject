package DataProvider;

import Model.Product;
import Model.ProductFactory;
import Model.ProductTypes;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class SQLDataProviderService implements DataProviderService {

    private final String DB_URL;
    private final String USER;
    private final String PASSWORD;
    private Connection CONNECTION;

    public SQLDataProviderService(String url, String user, String password) {
        this.DB_URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }

    public List<Product> provideData() {
        List<Product> supply = new LinkedList<>();
        try {
            setupConnection();
            String selectQuery = "SELECT * FROM Product";
            Statement stmt = CONNECTION.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectQuery);
            while(resultSet.next()) {
                supply.add(translateResultToProduct(resultSet));
            }
            closeConnection();
        } catch (SQLException e) {
            System.out.println("Error while setting up DB Connection");
        }
        return supply;
    }

    private void setupConnection() throws SQLException {
        if (CONNECTION == null || CONNECTION.isClosed()) {
            CONNECTION = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }
    }

    private void closeConnection() {
        if (CONNECTION != null) {
            try {
                CONNECTION.close();
            } catch (SQLException e) {
                System.err.println("Error while closing DB Connection.");
            }
        }
    }

    private static Product translateResultToProduct(ResultSet resultSet) throws SQLException {
        ProductTypes.valueOf(resultSet.getString(2));
        String name = resultSet.getString(3);
        LocalDate expiredAt = resultSet.getDate(4).toLocalDate();
        LocalDate addedToSupply = resultSet.getDate(5).toLocalDate();
        BigDecimal price = resultSet.getBigDecimal(6);
        int quality = resultSet.getInt(7);

        return ProductFactory.create(ProductTypes.valueOf(resultSet.getString(2)), name, expiredAt,
                addedToSupply, price, quality);
    }
}
