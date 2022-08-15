package sample;

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
    
    public Double getPrice(String travelId) {

        Integer travelTimeSeconds = travelTimeCalculator.getTravelTime(travelId);
        Integer travelTimeMinutes = travelTimeCalculator.getTravelTimeMinutes(travelTimeSeconds, true);
        Double travelRate = travelRateRepository.getTravelRatePerMinute(travelId);

        return travelTimeMinutes * travelRate;
    }
}
