package store.model;

import camp.nextstep.edu.missionutils.DateTimes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Promotion {
    private String name;
    private int buy;
    private int get;
    private String startDateStr;
    private String endDateStr;

    public Promotion(String name, int buy, int get, String startdateStr, String enddateStr) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDateStr = startdateStr;
        this.endDateStr = enddateStr;
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

    public int checkPromotionDate() {
        final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startDate = DATE_FORMAT.parse(startDateStr);
            Date endDate = DATE_FORMAT.parse(endDateStr);
            Date now = DATE_FORMAT.parse(String.valueOf(DateTimes.now()));

            if (now.before(startDate) && now.after(endDate)) {
                return buy;
            }
        } catch (ParseException e) {
            return 0;
        }
        return 0;
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

    public String getStartDateStr() {
        return startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }
}
