package util;

import java.util.ArrayList;
import java.util.List;

import bean.Clothing;
import bean.ClothingTaxStrategy;
import bean.Food;
import bean.FoodTaxStrategy;
import bean.GeneralProduct;
import bean.Product;

public class ReceiptUtil {

    public static String stateConverter(String input) {
        String[] parts = input.split(", ");
        // 從parts[0]開始解析state
        String state = parts[0].split(":")[1].trim();

        return state;
    }

    public static List<Product> productConverter(String input) {
        String[] parts = input.split(", ");
        List<Product> products = new ArrayList<>();
        // 從parts[1]開始解析商品
        for (int i = 1; i < parts.length; i++) {
            String[] itemParts = parts[i].split(" at ");
            String[] quantityAndName = itemParts[0].split(" ", 2);

            int quantity = Integer.parseInt(quantityAndName[0]);
            String name = quantityAndName[1];
            double price = Double.parseDouble(itemParts[1]);

            Product product;
            switch (name) {
                case "potato chips" -> product = new Product(name, price, quantity, new FoodTaxStrategy());
                case "shirt" -> product = new Product(name, price, quantity, new ClothingTaxStrategy());
                default -> product = new Product(name, price, quantity, new GeneralProduct());
            }
            products.add(product);
        }
        return products;
    }

    public static double getSubtotal(List<Product> products) {
        return products.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }

    public static double getTax(List<Product> products, String state) {
        return products.stream()
                .mapToDouble(product -> product.getTax(state))
                .sum();
    }
}