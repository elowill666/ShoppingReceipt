package bean;

import taxstrategy.TaxStrategy;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private TaxStrategy taxStrategy;

    public Product(String name, double price, int quantity, TaxStrategy taxStrategy) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.taxStrategy = taxStrategy;
    }

    public double getSubtotal() {
        return price * quantity;
    }

    public double getTax(String state) {
        return taxStrategy.getTax(this, state);
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

    public TaxStrategy getTaxStrategy() {
        return taxStrategy;
    }
}
