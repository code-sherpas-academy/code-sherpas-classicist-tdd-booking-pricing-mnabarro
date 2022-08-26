package sample;

import org.decimal4j.util.*;
public class TravelPriceCalculator {

    private final TravelTimeCalculator travelTimeCalculator;
    private final RateRepository rateRepository;
    private final DiscountRepository discountRepository;

    public TravelPriceCalculator(TravelTimeCalculator travelTimeCalculator,
                                 RateRepository rateRepository,
                                 DiscountRepository discountRepository) {
        
        this.travelTimeCalculator = travelTimeCalculator;
        this.rateRepository = rateRepository;
        this.discountRepository = discountRepository;
    }

    public Double getPrice() {

        double finalTravelPrice;

        Integer travelTimeSeconds = travelTimeCalculator.getTravelTime();
        Integer travelTimeMinutes = travelTimeCalculator.secondsToMinutes(travelTimeSeconds);
        Double travelRate = rateRepository.getTravelRatePerMinute();
        Double discountFactor = discountRepository.getTravelDiscount() * 0.01;

        Double fullTravelPrice = travelTimeMinutes * travelRate;
        Double travelPriceWithDiscount = fullTravelPrice * ( 1 - discountFactor);

        finalTravelPrice = DoubleRounder.round(travelPriceWithDiscount, 3);


        return finalTravelPrice;
    }
}
