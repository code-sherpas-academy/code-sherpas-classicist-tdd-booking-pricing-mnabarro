package sample;

import java.util.Random;

public class TravelTimeCalculator {

    public Integer getTravelTime (String travelId) {

        return new Random().nextInt(1000);
    }

    public Integer getTravelTimeMinutes(Integer timeInSeconds, boolean roundUpToNexMinute) {

        int result;
        int plusOne = 0;

        if (timeInSeconds < 60) {
            return 0;
        }

        if (roundUpToNexMinute) {
            if ( timeInSeconds % 60 > 0) {
                plusOne = 1;
            }
        }
        result = timeInSeconds / 60 + plusOne;

        return result;
    }

}

