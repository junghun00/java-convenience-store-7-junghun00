package store.controller;

import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.List;
import store.model.Order;
import store.model.Product;
import store.model.Store;
import store.service.StoreService;
import store.utils.ErrorMessage;
import store.view.InputView;
import store.view.OutputView;

public class StoreController {
    private final InputView inputView;
    private final OutputView outputView;
    private final StoreService storeService;

    public StoreController(InputView inputView, OutputView outputView, StoreService storeService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.storeService = storeService;
    }

    public void run() {
        List<Store> store = printProductList();
        purchaseProduct(store);
    }

    private List<Store> printProductList() {
        List<Store> store = new ArrayList<>();

        storeService.loadProductsFrom(store);
        outputView.printProductList(store);
        return store;
    }

    private void purchaseProduct(List<Store> store) {
        while (true) {
            try {
                String inputOrder = inputView.inputPurchaseProduct();
                Order order = new Order(inputOrder);
                break;
            } catch (IllegalArgumentException e) {
               outputView.printError("올바르지 않은 형식으로 입력했습니다.");
            }
        }
    }

}
