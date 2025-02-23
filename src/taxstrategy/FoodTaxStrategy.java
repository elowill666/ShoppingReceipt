package taxstrategy;

import bean.Product;

public class FoodTaxStrategy implements TaxStrategy {

    @Override
    public double getTax(Product product, String state) {
        return 0.0;
    }
}
