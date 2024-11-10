package store.model;

public class Receipt {
    private String name;
    private int individualPrice;
    private int quantity;
    private int promotionQuantity;
    private int promotionBuy;

    public Receipt(String name, int individualPrice, int quantity, int promotionBuy) {
        this.name = name;
        this.individualPrice = individualPrice;
        this.quantity = quantity;
        this.promotionBuy = promotionBuy;
        System.out.println(this);
    }

    public void quantityPlus() {
        quantity++;
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

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public int getPromotionBuy() {
        return promotionBuy;
    }

    public void setPromotionQuantity(int promotionQuantity) {
        this.promotionQuantity = promotionQuantity;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "name='" + name + '\'' +
                ", individualPrice=" + individualPrice +
                ", quantity=" + quantity +
                ", promotionQuantity=" + promotionQuantity +
                ", promotionBuy=" + promotionBuy +
                '}';
    }
}


