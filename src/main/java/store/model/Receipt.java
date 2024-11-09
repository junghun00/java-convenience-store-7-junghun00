package store.model;

public class Receipt {
    private String name;
    private int individualPrice;
    private int quantity;
    private int promotionBuy;

    public Receipt(String name, int individualPrice, int quantity, int promotionBuy) {
        this.name = name;
        this.individualPrice = individualPrice;
        this.quantity = quantity;
        this.promotionBuy = promotionBuy;
    }

    public String getName() {
        return name;
    }

    public int getIndividualPrice() {
        return individualPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPromotionBuy() {
        return promotionBuy;
    }
}


