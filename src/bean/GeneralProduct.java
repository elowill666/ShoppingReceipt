package bean;

public class GeneralProduct implements TaxStrategy {

    @Override
    public double getTax(Product product, String state) {
        return Math.ceil(Product.TAX.get(state) * product.getPrice() * product.getQuantity() * 20) / 20;
    }
}
