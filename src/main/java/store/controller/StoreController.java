package store.controller;

import java.util.ArrayList;
import java.util.List;
import store.model.Order;
import store.model.Promotion;
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
        List<Store> store = printProductList();
        List<Promotion> promotions = loadPromotions();
        Order order = purchaseProduct(store);

        storeService.calculatorPrice(store, order, promotions);
    }

    private List<Store> printProductList() {
        List<Store> store = new ArrayList<>();
        storeService.loadProductsForm(store);
        outputView.printProductList(store);
        return store;
    }

    private List<Promotion> loadPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        storeService.loadPromotionsForm(promotions);
        return promotions;
    }

    private Order purchaseProduct(List<Store> store) {
        while (true) {
            try {
                String inputOrder = inputView.inputPurchaseProduct();
                Order order = new Order(inputOrder);
                storeService.checkProduct(store, order);
                return order;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
