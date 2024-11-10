package store.view;

import static store.utils.ErrorMessage.ERROR;
import static store.utils.ErrorMessage.RETRY;

import java.util.List;
import store.model.Store;

public class OutputView {
    private final String WECOME_MESSAGE = "안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n";
    private static final String ERROR_ANSWER = "[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.";

    public void printError(String errorMessage) {
        System.out.print(ERROR + errorMessage + RETRY);
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
}