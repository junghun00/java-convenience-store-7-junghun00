package store.service;

import static store.model.Store.*;
import static store.utils.ErrorMessage.INVALID_FILE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import store.model.Store;

public class StoreService {

    public List<Store> loadProductsFrom(List<Store> store) {
        try (InputStream in = Store.class.getResourceAsStream("/products.md");
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                addProduct(store, line);
            }
        } catch (IOException e) {
            System.out.println(INVALID_FILE);
        }
        return store;
    }

    private void addProduct(List<Store> store, String line) {
        checkNotPromotionProduct(store, line);
        store.add(parseLine(line));
    }
}
