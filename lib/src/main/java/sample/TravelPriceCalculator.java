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

        Double finalTravelPrice;

        Integer travelTimeSeconds = travelTimeCalculator.getTravelTime();
        Integer travelTimeMinutes = travelTimeCalculator.secondsToMinutes(travelTimeSeconds);
        Double rate = rateRepository.travelRatePerMinute();
        Double travelDiscountFactor = discountRepository.travelDiscount() * 0.01;
        Double rateDiscountFactor = discountRepository.rateDiscount() * 0.01;

        Double rateWithDiscount = rate * ( 1 - rateDiscountFactor);
        Double fullTravelPrice = travelTimeMinutes * rateWithDiscount;

        Double priceWithDiscount = fullTravelPrice * ( 1 - travelDiscountFactor);

        finalTravelPrice = DoubleRounder.round(priceWithDiscount, 3);

        return finalTravelPrice;
    }

    public Double getPriceWithPass() {

        Double finalTravelPrice = .0;
        Integer minutesWithDiscount = 2;
        Integer minutesWithFullPrice = 0;

        Integer travelTimeSeconds = travelTimeCalculator.getTravelTime();
        Integer travelTimeMinutes = travelTimeCalculator.secondsToMinutes(travelTimeSeconds);
        minutesWithFullPrice = travelTimeMinutes - minutesWithDiscount;

        Double passRate = rateRepository.passRatePerMinute();

        Integer passDiscountFactor = discountRepository.passDiscount();
        Integer passRateDiscountFactor = discountRepository.passRateDiscount();

        Double passRateWithDiscount = passRate * (1 - passDiscountFactor * 0.01);
        Double passRateFirstMinutes = passRate * (1 - passRateDiscountFactor * 0.01);

        if (minutesWithFullPrice < 0) {
            minutesWithFullPrice = 0;
        }

        finalTravelPrice = minutesWithDiscount * passRateFirstMinutes +  minutesWithFullPrice * passRateWithDiscount;

        return DoubleRounder.round(finalTravelPrice, 3);
    }
}
