package bean;

public class Clothing extends Product {

    public Clothing(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public double getTax(String state) {
        if (state.equals("CA")) {
            return Math.ceil(TAX.get(state) * this.price * quantity * 20) / 20;
        }
        return 0.0;
    }

}
