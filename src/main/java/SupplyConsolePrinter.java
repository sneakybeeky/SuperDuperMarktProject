import DataProvider.CSVDataProviderService;
import DataProvider.DataProviderService;
import DataProvider.SQLDataProviderService;
import Model.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SupplyConsolePrinter {

    private final static String[] HEADERS = {"Produkt", "Preis", "Qualität", "Status"};

    private static void addHeader(StringBuilder output, LocalDate currentDay) {
        output.append(currentDay != null ? ("Bestand für den " + (currentDay)) : "Startbestand").append("\n");
        output.append(String.format("%-15s %-6s %-10s %-10s%n", HEADERS[0], HEADERS[1], HEADERS[2], HEADERS[3]));
        output.append("-".repeat(70)).append("\n");
    }

    public static void printStartSupply(List<Product> supply) {
        StringBuilder output = new StringBuilder();

        addHeader(output, null);
        supply.stream().filter(Objects::nonNull)
                .forEach(n -> output.append(String.format("%-15s %-6s %-10s %-10s%n", n.getName(),
                        n.getPrice(), n.getQuality(), n.calculateStatus(LocalDate.now()))));

        System.out.println(output);
    }

    public static void simulateTimeFrame(List<Product> supply,int amountOfDays) {
        for (int i = 0; i <amountOfDays; i++) {
            StringBuilder output = new StringBuilder();
            LocalDate currentDay = LocalDate.now().plusDays(i);
            addHeader(output, currentDay);
            supply.stream().filter(Objects::nonNull)
                    .forEach(n -> output.append(String.format("%-15s %-6s %-10s %-10s%n", n.getName(),
                            n.calculatePrice(currentDay), n.calculateQuality(currentDay), n.calculateStatus(currentDay))));

            System.out.println(output);
            cleanUpEndOfDay(supply, currentDay);
        }
    }

    private static void cleanUpEndOfDay(List<Product> supply, LocalDate currentDay) {
        supply.removeIf(i -> !i.calculateStatus(currentDay));
    }
}
