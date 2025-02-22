public class ShoppingReceipt {

    private static final String TESTCASE_1 = "Location: CA, 1 book at 17.99, 1 potato chips at 3.99";
    private static final String TESTCASE_2 = "Location: NY, 1 book at 17.99, 3 pencils at 2.99";
    private static final String TESTCASE_3 = "Location: NY, 2 pencils at 2.99, 1 shirt at 29.99";

    public static void main(String[] args) {
        ReceiptService receiptService = new ReceiptService();
        receiptService.payment(TESTCASE_1);
        receiptService.payment(TESTCASE_2);
        receiptService.payment(TESTCASE_3);
    }

}
