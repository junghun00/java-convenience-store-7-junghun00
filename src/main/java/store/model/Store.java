package store.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Store {
    private final String name;
    private int price;
    private int quantity;
    private String promotion;

    public Store(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public static List<Store> loadProductsFrom(String fileName) {
        List<Store> products = new ArrayList<>();

        try (InputStream in = Store.class.getResourceAsStream("/" + fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                checkNotPromotionProduct(products, line);
                products.add(parseLine(line));
            }
        } catch (IOException e) {
            System.out.println("");
        }
        return products;
    }

    private static void checkNotPromotionProduct(List<Store> products, String line) {
        if (products.isEmpty()) {
            return ;
        }

        Store product = products.getLast();
        if (!line.contains(product.getName()) && product.getPromotion() != "") {
            products.add(new Store(product.getName(), product.getPrice(), 0, ""));
        }
    }

    private static Store parseLine(String line) {
        List<String> product = Arrays.asList(line.split(","));

        String name = product.get(0).trim();
        int price = Integer.parseInt(product.get(1).trim());
        int quantity = Integer.parseInt(product.get(2).trim());
        String promotion = checkPromotion(product.get(3));

        return new Store(name, price, quantity, promotion);
    }

    private static String checkPromotion(String promotion) {
        if (promotion.equals("null")) {
            return "";
        }
        return promotion.trim();
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getPromotion() {
        return promotion;
    }

    @Override
    public String toString() {
        String formatPrice = String.format("%,d", price);
        if (quantity == 0) {
            return "- " +
                    name + " " +
                    formatPrice + "원 " +
                    "재고 없음";
        }
        return "- " +
                name + " " +
                formatPrice + "원 " +
                quantity + "개 " +
                promotion;
    }
}
