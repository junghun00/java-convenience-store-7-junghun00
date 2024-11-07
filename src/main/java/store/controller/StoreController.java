package store.controller;

import java.util.ArrayList;
import java.util.List;
import store.model.Store;
import store.service.StoreService;
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
        List<Store> products = printProductList();
//        purchaseProduct(products);
    }

    private List<Store> printProductList() {
        List<Store> store = new ArrayList<>();

        storeService.loadProductsFrom(store);
        outputView.printProductList(store);
        return store;
    }
}
