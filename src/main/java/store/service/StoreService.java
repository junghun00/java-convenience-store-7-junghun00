package store.service;

import java.util.List;
import store.model.Store;

public class StoreService {
    public List<Store> loadProducts() {
        return Store.loadProductsFrom("products.md");
    }


}
