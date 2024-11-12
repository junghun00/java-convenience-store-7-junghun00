package store.view;

import static store.utils.ErrorMessage.ERROR;
import static store.utils.ErrorMessage.RETRY;

import java.util.List;
import store.model.Receipt;
import store.model.Store;

public class OutputView {
    private static final String ERROR_ANSWER = "[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.";

    private final String WECOME_MESSAGE = "안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n";
    private final String RESULT_START_MESSAGE = "\n==============W 편의점================";
    private final String RESULT_MESSAGE = "%-16s\t%-5s\t%s\n";
    private final String ORDER_LIST = "%-16s\t%-7d\t%,d\n";
    private final String PROMOTION_MESSAGE = "=============증     정===============";
    private final String PROMOTION_LIST = "%-16s\t%,-5d\n";
    private final String LINE = "====================================";


    public void printError(String errorMessage) {
        System.out.println(ERROR + errorMessage + RETRY);
    }

    public static void printErrorAnswer() {
        System.out.println(ERROR_ANSWER);
    }

    public void printProductList(List<Store> products) {
        System.out.println(WECOME_MESSAGE);

        for (Store product : products) {
            System.out.println(product);
        }
    }

    public void printReceipt(List<Receipt> receipts, int discount) {
        System.out.println(RESULT_START_MESSAGE);
        System.out.printf(RESULT_MESSAGE, "상품명", "수량", "금액");
        int totalOrderPrice = printOrder(receipts);

        System.out.println(PROMOTION_MESSAGE);
        int totalPromotionPrice = printPromotion(receipts);

        System.out.println(LINE);
        printPaymentPrice(receipts, totalOrderPrice, totalPromotionPrice, discount);
    }

    private int printOrder(List<Receipt> receipts) {
        int totalOrder = 0;

        for (Receipt receipt : receipts) {
            int price = receipt.getIndividualPrice() * receipt.getQuantity();
            totalOrder += price;
            System.out.printf(ORDER_LIST, receipt.getName(), receipt.getQuantity(), price);
        }

        return totalOrder;
    }

    private int printPromotion(List<Receipt> receipts) {
        int totalPromotion = 0;

        for (Receipt receipt : receipts) {
            if (receipt.getPromotionQuantity() != 0) {
                int price = receipt.getIndividualPrice() * (receipt.getPromotionQuantity() / (receipt.getPromotionBuy() + 1));
                totalPromotion += price;
                System.out.printf(PROMOTION_LIST, receipt.getName(), receipt.getPromotionQuantity() / (receipt.getPromotionBuy() + 1));
            }
        }

        return totalPromotion;
    }

    private void printPaymentPrice(List<Receipt> receipts, int totalOrder, int totalPromotion, int discount) {
        int totalQuantity = 0;
        for (Receipt receipt : receipts) {
            totalQuantity += receipt.getQuantity();
        }
        System.out.printf("%-16s\t%-7d\t%,-7d\n", "총구매액", totalQuantity, totalOrder);
        System.out.printf("%-23s\t-%,d\n", "행사할인", totalPromotion);
        System.out.printf("%-23s\t-%,d\n", "멤버십할인", discount);
        System.out.printf("%-23s\t%,d\n", "내실돈", (totalOrder - totalPromotion - discount));
    }
}