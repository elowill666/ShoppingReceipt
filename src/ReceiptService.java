import java.util.List;

import bean.Product;
import util.ReceiptUtil;

public class ReceiptService {

    public void payment(String input) {
        // get state
        String state = ReceiptUtil.stateConverter(input);
        // get products
        List<Product> products = ReceiptUtil.productConverter(input);

        System.out.printf("%-18s%-17s%-10s%n", "item", "price", "qty");

        double subtotal = ReceiptUtil.getSubtotal(products);

        products.forEach(product -> {
            String formattedPrice = String.format("$%.2f", product.getPrice());
            System.out.printf("%-16s%7s%15d%n", product.getName(), formattedPrice, product.getQuantity());
        });
        double tax = ReceiptUtil.getTax(products, state);

        double total = subtotal + tax;

        System.out.printf("%-32s$%1.2f%n", "subtotal:", subtotal);
        System.out.printf("%-33s$%1.2f%n", "tax:", tax);
        System.out.printf("%-32s$%1.2f%n", "total:", total);
        System.out.println();
    }
}
