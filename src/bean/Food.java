package bean;

public class Food extends Product {

    public Food(String name, double price, int quantity, TaxStrategy taxStrategy) {
        super(name, price, quantity, taxStrategy);
    }

}
