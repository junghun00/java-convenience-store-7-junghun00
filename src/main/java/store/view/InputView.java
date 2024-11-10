package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final String GUIDE_MESSAGE = "\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String GET_PROMOTION_MESSAGE = "현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";

    public String inputPurchaseProduct() {
        System.out.println(GUIDE_MESSAGE);
        return Console.readLine();
    }

    public static String getFree(String name) {
        System.out.printf(GET_PROMOTION_MESSAGE, name);
        return Console.readLine();
    }
}
