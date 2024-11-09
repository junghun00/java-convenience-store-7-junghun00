package store.model;

import java.util.Arrays;
import java.util.List;

public class Promotion {
    private String name;
    private int buy;
    private int get;
    private String startDate;
    private String endDate;

    public Promotion(String name, int buy, int get, String start_date, String end_date) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = start_date;
        this.endDate = end_date;
    }

    public static Promotion parsePromotions(String line) {
        List<String> promotion = Arrays.asList(line.split(","));

        String name = promotion.get(0).trim();
        int buy = Integer.parseInt(promotion.get(1).trim());
        int get = Integer.parseInt(promotion.get(2).trim());
        String start_date = promotion.get(3).trim();
        String end_date = promotion.get(4).trim();

        return new Promotion(name, buy, get, start_date, end_date);
    }

    public String getName() {
        return name;
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
