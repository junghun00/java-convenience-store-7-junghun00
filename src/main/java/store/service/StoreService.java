package store.service;

import static store.model.Store.*;
import static store.utils.ErrorMessage.INVALID_NAME;
import static store.utils.ErrorMessage.INVALID_QUANTITY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.List;
import store.model.Order;
import store.model.Product;
import store.model.Store;

public class StoreService {

    public void loadProductsFrom(List<Store> store) {
        try (InputStream in = Store.class.getResourceAsStream("/products.md");
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                addProduct(store, line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addProduct(List<Store> store, String line) {
        checkNotPromotionProduct(store, line);
        store.add(parseLine(line));
    }

    public void checkProduct(List<Store> store, Order order) {
        for (Product product : order.getOrder()) {
            checkName(store, product);
            checkQuantity(store, product);
        }
    }

    private void checkName(List<Store> store, Product product) {
        for (Store storeProduct : store) {
            if (storeProduct.getName().equals(product.getName())) {
                return;
            }
        }

        throw new IllformedLocaleException(INVALID_NAME);
    }

    private void checkQuantity(List<Store> store, Product product) {
        int quantity = 0;

        for (Store storeProduct : store) {
            if (storeProduct.getName().equals(product.getName())) {
                quantity += storeProduct.getQuantity();
            }
        }

        if (quantity < product.getQuantity()) {
            throw new IllformedLocaleException(INVALID_QUANTITY);
        }
    }

    public List<String> checkPromotion(List<Store> store, Order order) {
        List<String> promotionType = new ArrayList<>();

        for (Product product : order.getOrder()) {
            promotionType.add(getPromotion(store, product));
        }

        return promotionType;
    }


    private String getPromotion(List<Store> store, Product product) {
        for (Store storeProduct : store) {
            if (storeProduct.getName().equals(product.getName())) {
                return storeProduct.getPromotion();
            }
        }
        return "";
    }
}
