package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String GET_PROMOTION_MESSAGE = "\n현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n";
    private static final String PAYMENT_MESSAGE = "\n현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n";
    private static final String MEMBERSHIP_MESSAGE = "\n멤버십 할인을 받으시겠습니까? (Y/N)";

    private final String GUIDE_MESSAGE = "\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private final String END_MESSAGE = "\n감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";

    public String inputPurchaseProduct() {
        System.out.println(GUIDE_MESSAGE);
        return Console.readLine();
    }

    public String inputEndMessage() {
        System.out.println(END_MESSAGE);
        return Console.readLine();
    }

    public static String getFree(String name) {
        System.out.printf(GET_PROMOTION_MESSAGE, name);
        return Console.readLine();
    }

    public static String askForPayment(String name, int quantity) {
        System.out.printf(PAYMENT_MESSAGE, name, quantity);
        return Console.readLine();
    }

    public static String membershipMessage() {
        System.out.println(MEMBERSHIP_MESSAGE);
        return Console.readLine();
    }
}
