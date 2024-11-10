package store.service;

import static store.model.Promotion.parsePromotions;
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
import store.model.Promotion;
import store.model.Receipt;
import store.model.Store;
import store.view.InputView;
import store.view.OutputView;

public class StoreService {
    private static final int NON_PROMOTION = 0;
    private static final int GET = 1;
    private static final int MAX_MEMBERSHIP = 8000;

    public void loadProductsForm(List<Store> store) {
        try (InputStream in = Store.class.getResourceAsStream("/products.md"); BufferedReader br = new BufferedReader(
                new InputStreamReader(in))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                addProduct(store, line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadPromotionsForm(List<Promotion> promotions) {
        try (InputStream in = Store.class.getResourceAsStream("/promotions.md"); BufferedReader br = new BufferedReader(
                new InputStreamReader(in))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                promotions.add(parsePromotions(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addProduct(List<Store> store, String line) {
        checkNotPromotionProduct(store, line);
        store.add(parseStore(line));
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

    public List<Receipt> calculatorPrice(List<Store> store, Order order, List<Promotion> promotions) {
        List<Receipt> receipts = new ArrayList<>();

        for (Product product : order.getOrder()) {
            String name = product.getName();
            int indivialPrice = getPrice(store, product.getName());
            int quantity = product.getQuantity();
            int promotinBuy = checkPromotionProduct(store, product, promotions);
            receipts.add(new Receipt(name, indivialPrice, quantity, promotinBuy));
        }
        return receipts;
    }

    private int getPrice(List<Store> store, String productName) {
        int price = 0;
        for (Store storeProduct : store) {
            if (storeProduct.getName().equals(productName)) {
                price = storeProduct.getPrice();
            }
        }

        return price;
    }

    private int checkPromotionProduct(List<Store> store, Product product, List<Promotion> promotions) {
        for (Store storeProduct : store) {
            if (storeProduct.getName().equals(product.getName())) {
                return getPromotion(storeProduct.getPromotion(), promotions);
            }
        }

        return 0;
    }

    private int getPromotion(String promotion, List<Promotion> promotions) {
        for (Promotion promotionType : promotions) {
            if (promotionType.getName().equals(promotion)) {
                return promotionType.checkPromotionDate();
            }
        }
        return 0;
    }

    public void checkTribeQuantity(List<Receipt> receipts, List<Store> store) {
        for (Receipt receipt : receipts) {
            if (receipt.getPromotionBuy() != NON_PROMOTION) {
                checkPromotionQuantity(receipt, store);
            }
        }
    }

    private void checkPromotionQuantity(Receipt receipt, List<Store> store) {
        for (Store storeProduct : store) {
            if (receipt.getName().equals(storeProduct.getName())) {
                checkNonGet(receipt, storeProduct.getQuantity());
            }
        }
    }

    private void checkNonGet(Receipt receipt, int storeQuantity) {
        if (receipt.getQuantity() % (receipt.getPromotionBuy() + GET) == receipt.getPromotionBuy()) {
            if (isGet(receipt, storeQuantity)) {
                receipt.quantityPlus();
            }
        }
    }

    private boolean isGet(Receipt receipt, int storeQuantity) {
        if (receipt.getQuantity() + GET <= storeQuantity) {
            while (true) {
                try {
                    return answer(InputView.getFree(receipt.getName()));
                } catch (IllegalArgumentException e) {
                    OutputView.printErrorAnswer();
                }
            }
        }
        return false;
    }

    private boolean answer(String answer) {
        if (answer.equals("Y")) {
            return true;
        }
        if (answer.equals("N")) {
            return false;
        }

        throw new IllformedLocaleException();
    }

    public void checkTribePromotion(List<Receipt> receipts, List<Store> store) {
        for (Receipt receipt : receipts) {
            if (receipt.getPromotionBuy() != NON_PROMOTION) {
                checkStoreQuantity(receipt, store);
            }
        }
    }

    private void checkStoreQuantity(Receipt receipt, List<Store> store) {
        for (Store storeProduct : store) {
            if (receipt.getName().equals(storeProduct.getName())) {
                checkOverGet(receipt, storeProduct);
            }
        }
    }

    private void checkOverGet(Receipt receipt, Store storeProduct) {
        int promotionProductQuantity = storeProduct.getQuantity()
                - (storeProduct.getQuantity() % receipt.getPromotionBuy() + GET);
        System.out.println("promotionProductQuantity = " + promotionProductQuantity);
        int nomDiscountableQuantity = receipt.getQuantity() - promotionProductQuantity;

        if (nomDiscountableQuantity > 0) {
            if (!isPaymentConfirmed(receipt, nomDiscountableQuantity)) {
                throw new IllformedLocaleException("다시입력받기 미구현");
            }
        }
        receipt.setPromotionQuantity(promotionProductQuantity);
    }

    private boolean isPaymentConfirmed(Receipt receipt, int nomDiscountableQuantity) {
        while (true) {
            try {
                return answer(InputView.askForPayment(receipt.getName(), nomDiscountableQuantity));
            } catch (IllegalArgumentException e) {
                OutputView.printErrorAnswer();
            }
        }
    }

    public int membershipDiscount(List<Receipt> receipts) {
        int totalPrice = 0;
        int promotionPrice = 0;

        if (isMembership()) {
            for (Receipt receipt : receipts) {
                totalPrice += receipt.getIndividualPrice() * receipt.getQuantity();
                promotionPrice += checkDiscount(receipt);
            }
        }

        return checkMaxDisCount(totalPrice, promotionPrice);
    }

    private boolean isMembership() {
        while (true) {
            try {
                return answer(InputView.membershipMessage());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorAnswer();
            }
        }
    }

    private int checkDiscount(Receipt receipt) {
        int promotionPrice = 0;
        if (receipt.getPromotionBuy() != NON_PROMOTION) {
            promotionPrice = receipt.getIndividualPrice() * receipt.getPromotionQuantity();
        }
        return promotionPrice;
    }

    private int checkMaxDisCount(int totalPrice, int promotionPrice) {
        int disCount = (int) ((totalPrice - promotionPrice) * 0.3);
        if (disCount > MAX_MEMBERSHIP) {
            return MAX_MEMBERSHIP;
        }

        return disCount;
    }
}
