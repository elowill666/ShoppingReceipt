package bean;

public interface TaxStrategy {
    double getTax(Product product, String state);
}
