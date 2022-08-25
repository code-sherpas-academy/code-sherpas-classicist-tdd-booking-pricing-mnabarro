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
    //If no discount is applied, we don´t need TravelDiscountRepository
    public TravelPriceCalculator(TravelTimeCalculator travelTimeCalculator,
                                 TravelRateRepository travelRateRepository) {

        this.travelTimeCalculator = travelTimeCalculator;
        this.travelRateRepository = travelRateRepository;
        this.travelDiscountRepository = null;
    }
    
    public Double getPrice() {

        double result;

        Integer travelTimeSeconds = travelTimeCalculator.getTravelTime();
        Integer travelTimeMinutes = travelTimeCalculator.secondsToMinutes(travelTimeSeconds, true);
        Double travelRate = travelRateRepository.getTravelRatePerMinute();

        result = DoubleRounder.round(travelTimeMinutes * travelRate, 3);

        return result;
    }
}
