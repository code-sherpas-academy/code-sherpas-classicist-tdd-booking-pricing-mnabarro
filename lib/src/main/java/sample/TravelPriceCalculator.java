package sample;

import org.decimal4j.util.*;
public class TravelPriceCalculator {

    private final TravelTimeCalculator travelTimeCalculator;
    private final TravelRateRepository travelRateRepository;
    private final TravelDiscountRepository travelDiscountRepository;

    public TravelPriceCalculator(TravelTimeCalculator travelTimeCalculator,
                                 TravelRateRepository travelRateRepository,
                                 TravelDiscountRepository travelDiscountRepository) {
        
        this.travelTimeCalculator = travelTimeCalculator;
        this.travelRateRepository = travelRateRepository;
        this.travelDiscountRepository = travelDiscountRepository;
    }

    public Double getPrice() {

        double finalTravelPrice;

        Integer travelTimeSeconds = travelTimeCalculator.getTravelTime();
        Integer travelTimeMinutes = travelTimeCalculator.secondsToMinutes(travelTimeSeconds, true);
        Double travelRate = travelRateRepository.getTravelRatePerMinute();
        Double discountFactor = travelDiscountRepository.getTravelDiscount() * 0.01;

        Double fullTravelPrice = travelTimeMinutes * travelRate;
        Double travelPriceWithDiscount = fullTravelPrice * ( 1 - discountFactor);

        finalTravelPrice = DoubleRounder.round(travelPriceWithDiscount, 3);


        return finalTravelPrice;
    }
}
