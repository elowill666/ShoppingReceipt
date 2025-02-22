package bean;

import java.util.HashMap;
import java.util.Map;

public class Product {
    protected String name;
    protected double price;
    protected int quantity;

    protected static final Map<String, Double> TAX;

    static {
        TAX = new HashMap<>();
        TAX.put("CA", 0.0975);
        TAX.put("NY", 0.08875);
    }

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return price * quantity;
    }

    public double getTax(String state) {
        return Math.ceil(TAX.get(state) * this.price * quantity * 20) / 20;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

}
