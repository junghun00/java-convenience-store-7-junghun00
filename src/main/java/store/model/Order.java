package store.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> order;

    public Order(String inputOrder) {
        validateProductFormat(inputOrder);
        this.order = generateProducts(inputOrder);
    }

    public List<Product> generateProducts(String inputOrder) {
        List<Product> products = new ArrayList<>();
        String[] orders = inputOrder.split("],\\[");

        for (String order : orders) {
            String formatOrder = order.replace("[", "").replace("]", "");
            int quantityIndex = formatOrder.lastIndexOf('-') + 1;

            String name = formatOrder.substring(0, quantityIndex - 1).trim();
            String quantity = formatOrder.substring(quantityIndex).trim();

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
