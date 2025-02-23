package taxstrategy;

import bean.Product;

public class ClothingTaxStrategy implements TaxStrategy {

    @Override
    public double getTax(Product product, String state) {
        if (state.equals("CA")) {
            return Math.ceil(TAX.get(state) * product.getPrice() * product.getQuantity() * 20) / 20;
        }
        return 0.0;
    }
}
