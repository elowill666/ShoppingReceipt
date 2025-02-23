package taxstrategy;

import java.util.Map;

import bean.Product;

public interface TaxStrategy {

    Map<String, Double> TAX = Map.of(
            "CA", 0.0975,
            "NY", 0.08875);

    double getTax(Product product, String state);
}
