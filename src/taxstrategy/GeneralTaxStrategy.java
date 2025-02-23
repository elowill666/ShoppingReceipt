package taxstrategy;

import bean.Product;

public class GeneralTaxStrategy implements TaxStrategy {

    @Override
    public double getTax(Product product, String state) {
        return Math.ceil(TAX.get(state) * product.getPrice() * product.getQuantity() * 20) / 20;
    }
}
