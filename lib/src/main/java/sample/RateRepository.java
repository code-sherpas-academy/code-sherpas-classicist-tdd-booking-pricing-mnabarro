package sample;

import java.util.Random;

public class RateRepository {

    public Double travelRatePerMinute() {

        return new Random().nextDouble();
    }

    public Double passRatePerMinute() {

        return new Random().nextDouble();
    }
}
