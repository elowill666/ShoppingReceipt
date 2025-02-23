import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;

import bean.Product;
import taxstrategy.ClothingTaxStrategy;
import taxstrategy.FoodTaxStrategy;
import taxstrategy.GeneralTaxStrategy;
import util.ReceiptUtil;

public class ShoppingReceiptTest {

    private static final String TESTCASE_1 = "Location: CA, 1 book at 17.99, 1 potato chips at 3.99";
    private static final String TESTCASE_2 = "Location: NY, 1 book at 17.99, 3 pencils at 2.99";
    private static final String TESTCASE_3 = "Location: NY, 2 pencils at 2.99, 1 shirt at 29.99";

    private double calculateTax(double price, int quantity, double taxRate) {
        return Math.ceil(taxRate * price * quantity * 20) / 20;
    }

    @Test
    public void testProductConverter() {
        // get state
        List<Product> products = ReceiptUtil.productConverter(TESTCASE_1);
        List<Product> products2 = ReceiptUtil.productConverter(TESTCASE_2);
        List<Product> products3 = ReceiptUtil.productConverter(TESTCASE_3);

        assertNotNull(products);
        assertNotNull(products2);
        assertNotNull(products3);

    }

    @Test
    public void stateConverter() {
        // get state
        String state = ReceiptUtil.stateConverter(TESTCASE_1);
        String state2 = ReceiptUtil.stateConverter(TESTCASE_2);
        String state3 = ReceiptUtil.stateConverter(TESTCASE_3);
        assertEquals(state, "CA");
        assertEquals(state2, "NY");
        assertEquals(state3, "NY");

    }

    @Test
    public void testGetSubtotal() {
        List<Product> products = List.of(
                new Product("book", 17.99, 1, new GeneralTaxStrategy()),
                new Product("potato chips", 3.99, 1, new FoodTaxStrategy()));

        List<Product> products2 = List.of(
                new Product("book", 17.99, 1, new GeneralTaxStrategy()),
                new Product("pencils", 2.99, 3, new GeneralTaxStrategy()));

        List<Product> products3 = List.of(
                new Product("pencils", 2.99, 2, new GeneralTaxStrategy()),
                new Product("shirt", 29.99, 1, new ClothingTaxStrategy()));

        double subtotal = ReceiptUtil.getSubtotal(products);
        double subtotal2 = ReceiptUtil.getSubtotal(products2);
        double subtotal3 = ReceiptUtil.getSubtotal(products3);

        // TESTCASE_1
        assertEquals(subtotal, 17.99 * 1 + 3.99 * 1);
        // TESTCASE_2
        assertEquals(subtotal2, 17.99 * 1 + 2.99 * 3);
        // TESTCASE_3
        assertEquals(subtotal3, 2.99 * 2 + 29.99 * 1);
    }

    @Test
    public void testGetTax() {
        Product product = new Product("book", 17.99, 1, new GeneralTaxStrategy());
        Product food = new Product("potato chips", 3.99, 1, new FoodTaxStrategy());
        Product clothing = new Product("shirt", 29.99, 1, new ClothingTaxStrategy());

        // CA product Tax 0.0975
        assertEquals(product.getTax("CA"), calculateTax(product.getPrice(), product.getQuantity(), 0.0975));

        // NY product Tax 0.08875
        assertEquals(product.getTax("NY"), calculateTax(product.getPrice(), product.getQuantity(), 0.08875));

        // CA food Tax 0
        assertEquals(food.getTax("CA"), calculateTax(food.getPrice(), food.getQuantity(), 0));

        // NY food Tax 0
        assertEquals(food.getTax("NY"), calculateTax(food.getPrice(), food.getQuantity(), 0));

        // CA clothing Tax 0.0975
        assertEquals(clothing.getTax("CA"),
                calculateTax(clothing.getPrice(), clothing.getQuantity(), 0.0975));

        // NY clothing Tax 0
        assertEquals(clothing.getTax("NY"), calculateTax(clothing.getPrice(), clothing.getQuantity(), 0));

    }

    @Test
    public void testPayment() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ReceiptService ReceiptService = new ReceiptService();

        ReceiptService.payment(TESTCASE_1);
        ReceiptService.payment(TESTCASE_2);
        ReceiptService.payment(TESTCASE_3);

        String output = outputStream.toString();

        assertTrue(output.contains("item              price            qty"));
        assertTrue(output.contains("book             $17.99              1"));
        assertTrue(output.contains("potato chips      $3.99              1"));
        assertTrue(output.contains("subtotal:                       $21.98"));
        assertTrue(output.contains("tax:                             $1.80"));
        assertTrue(output.contains("total:                          $23.78"));

        assertTrue(output.contains("item              price            qty"));
        assertTrue(output.contains("book             $17.99              1"));
        assertTrue(output.contains("pencils           $2.99              3"));
        assertTrue(output.contains("subtotal:                       $26.96"));
        assertTrue(output.contains("tax:                             $2.40"));
        assertTrue(output.contains("total:                          $29.36"));

        assertTrue(output.contains("item              price            qty"));
        assertTrue(output.contains("pencils           $2.99              2"));
        assertTrue(output.contains("shirt            $29.99              1"));
        assertTrue(output.contains("subtotal:                       $35.97"));
        assertTrue(output.contains("tax:                             $0.55"));
        assertTrue(output.contains("total:                          $36.52"));
    }

}
