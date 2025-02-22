package bean;

public class Food extends Product {

    public Food(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public double getTax(String state) {
        return 0.0;
    }

}
