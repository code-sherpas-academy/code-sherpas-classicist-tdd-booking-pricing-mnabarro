package sample;

import java.util.Random;

public class TravelRateRepository {

    public Double getTravelRatePerMinute() {

        return new Random().nextDouble();
    }
}
