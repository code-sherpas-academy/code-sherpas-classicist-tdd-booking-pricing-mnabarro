package sample;

import java.util.Random;

public class TravelRateRepository {

    public Double getTravelRatePerMinute(String travelId) {

        return new Random().nextDouble();
    }
}
