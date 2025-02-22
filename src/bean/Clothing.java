package bean;

public class Clothing extends Product {

    public Clothing(String name, double price, int quantity, TaxStrategy taxStrategy) {
        super(name, price, quantity, taxStrategy);
    }
}
