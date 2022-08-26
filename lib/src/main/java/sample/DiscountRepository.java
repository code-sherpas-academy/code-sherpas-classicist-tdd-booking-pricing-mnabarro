package sample;

import java.util.Random;

public class DiscountRepository {

    public Integer getTravelDiscount () {

        return new Random().nextInt(30);
    }
    public Integer getRateDiscount () {

        return new Random().nextInt(30);
    }
}
