package store.view;

import java.util.List;
import store.model.Store;

public class OutputView {
    private final String WECOME_MESSAGE = "안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n";
    private final String GUIDE_MESSAGE = "\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";

    public void printProductList(List<Store> products) {
        System.out.println(WECOME_MESSAGE);

        for (Store product : products) {
            System.out.println(product);
        }

        System.out.println(GUIDE_MESSAGE);
    }
}