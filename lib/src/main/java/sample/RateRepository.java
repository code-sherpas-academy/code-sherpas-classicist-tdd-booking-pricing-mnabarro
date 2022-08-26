package sample;

import java.util.Random;

public class RateRepository {

    public Double getTravelRatePerMinute() {

        return new Random().nextDouble();
    }
}
