package sample;

import java.util.Random;

public class DiscountRepository {

    public Integer travelDiscount() {

        return new Random().nextInt(30);
    }
    public Integer rateDiscount() {

        return new Random().nextInt(30);
    }

    public Integer passDiscount() {

        return 0;
    }

    public Integer passRateDiscount() {

        return 0;
    }
}
