package store.model;


import static store.utils.ErrorMessage.INVALID_FORMAT;

public class Product {
    private final String name;
    private final int quantity;

    public Product(String name, String quantity) {
        validate(name, quantity);
        this.name = name;
        this.quantity = Integer.parseInt(quantity);
    }

    private void validate(String name, String quantity) {
        checkName(name);
        checkQuantity(quantity);
    }

    private void checkName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_FORMAT);
        }
    }

    private void checkQuantity(String quantity) {
        if (!quantity.matches("\\d+")) {
            throw new IllegalArgumentException(INVALID_FORMAT);
        }
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
