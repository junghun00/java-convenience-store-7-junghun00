package store.model;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private List<Product> products;

    public Products(String inputOrder) {
        validateProductFormat(inputOrder);
        this.products = generateProducts(inputOrder);
    }

    public List<Product> generateProducts(String input) {
        List<Product> products = new ArrayList<>();
        String[] items = input.split("],\\[");

        for (String item : items) {
            String nameStart = item.replace("[", "").replace("]", "");
            int quantityIndex = nameStart.lastIndexOf('-') + 1;

            String name = nameStart.substring(0, quantityIndex- 1).trim();
            String quantity = nameStart.substring(quantityIndex).trim();

            products.add(new Product(name, quantity));
        }
        return products;
    }

    private void validateProductFormat(String inputOrder) {
        String[] orderList = inputOrder.split(",");

        for (String order : orderList) {
            if (!isValidProductEntry(order.trim())) {
                throw new IllegalArgumentException("");
            }
        }
    }

    private boolean isValidProductEntry(String order) {
        if (!order.startsWith("[") || !order.endsWith("]")) {
            return false;
        }

        String part = order.substring(1, order.length() - 1);
        String[] parts = part.split("-");
        if (parts[0].isEmpty()) {
            return false;
        }

        return parts.length == 2;
    }
}
