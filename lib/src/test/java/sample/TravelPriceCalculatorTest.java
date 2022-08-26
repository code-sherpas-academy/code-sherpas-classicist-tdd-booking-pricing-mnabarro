package sample;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class TravelPriceCalculatorTest {

    @Test
    void seconds_to_minutes__times_shorter_than_one_minute_must_return_zero_other_rounds_to_next_full_minute () {
        TravelTimeCalculator travelTimeCalculator = new TravelTimeCalculator();

        Assertions.assertThat(travelTimeCalculator.secondsToMinutes(37)).isEqualTo(0);
        Assertions.assertThat(travelTimeCalculator.secondsToMinutes(121)).isEqualTo(3);
        Assertions.assertThat(travelTimeCalculator.secondsToMinutes(184)).isEqualTo(4);
    }
    @Test
    void calculate_price_for_900_second_travel_at_20cents_per_minute_rate () {

        TravelTimeCalculator travelTimeCalculator = mock(TravelTimeCalculator.class);
        RateRepository rateRepository = mock(RateRepository.class);
        DiscountRepository discountRepository = mock(DiscountRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(travelTimeCalculator, rateRepository, discountRepository);

        Integer travelTimeInSeconds = 900;
        Integer travelTimeInMinutes =  15;
        Double travelRateValue = 0.2;
        Double expectedPrice = 3.0 ;

        Mockito.when(travelTimeCalculator.getTravelTime()).thenReturn(travelTimeInSeconds);
        Mockito.when(travelTimeCalculator.secondsToMinutes(travelTimeInSeconds)).thenReturn(travelTimeInMinutes);
        Mockito.when(discountRepository.travelDiscount()).thenReturn(0);
        Mockito.when(discountRepository.rateDiscount()).thenReturn(0);
        Mockito.when(rateRepository.travelRatePerMinute()).thenReturn(travelRateValue);

        Double calculatedPrice = travelPriceCalculator.getPrice();

        Assertions.assertThat(calculatedPrice).isEqualTo(expectedPrice);
    }
    @Test
    void calculate_price_for_121_seconds_travel_to_be_rounded_to_3_minutes () {

        TravelTimeCalculator travelTimeCalculator = mock(TravelTimeCalculator.class);
        RateRepository rateRepository = mock(RateRepository.class);
        DiscountRepository discountRepository = mock(DiscountRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(travelTimeCalculator, rateRepository, discountRepository);

        Integer travelTimeInSeconds = 121;
        Integer travelTimeInMinutes =  3;
        Double travelTravelRateValue = 0.2;
        Double expectedPrice = 0.6 ;

        Mockito.when(travelTimeCalculator.getTravelTime()).thenReturn(travelTimeInSeconds);
        Mockito.when(travelTimeCalculator.secondsToMinutes(travelTimeInSeconds)).thenReturn(travelTimeInMinutes);
        Mockito.when(discountRepository.travelDiscount()).thenReturn(0);
        Mockito.when(discountRepository.rateDiscount()).thenReturn(0);
        Mockito.when(rateRepository.travelRatePerMinute()).thenReturn(travelTravelRateValue);

        Double calculatedPrice = travelPriceCalculator.getPrice();

        Assertions.assertThat(calculatedPrice).isEqualTo(expectedPrice);
    }
    @Test
    void calculate_price_for_travel_times_shorter_than_one_minute_should_be_free () {

        TravelTimeCalculator travelTimeCalculator = mock(TravelTimeCalculator.class);
        RateRepository rateRepository = mock(RateRepository.class);
        DiscountRepository discountRepository = mock(DiscountRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(travelTimeCalculator, rateRepository, discountRepository);

        Integer travelTimeInSeconds = 37;
        Integer travelTimeInMinutes =  0;
        Double travelRateValue = 0.2;
        Double expectedPrice = 0.0 ;

        Mockito.when(travelTimeCalculator.getTravelTime()).thenReturn(travelTimeInSeconds);
        Mockito.when(travelTimeCalculator.secondsToMinutes(travelTimeInSeconds)).thenReturn(travelTimeInMinutes);
        Mockito.when(discountRepository.travelDiscount()).thenReturn(0);
        Mockito.when(discountRepository.rateDiscount()).thenReturn(0);
        Mockito.when(rateRepository.travelRatePerMinute()).thenReturn(travelRateValue);

        Double calculatedPrice = travelPriceCalculator.getPrice();

        Assertions.assertThat(calculatedPrice).isEqualTo(expectedPrice);
    }

    @Test
    void calculate_price_for_121_seconds_travel_with_20_percent_total_discount () {

        TravelTimeCalculator travelTimeCalculator = mock(TravelTimeCalculator.class);
        RateRepository rateRepository = mock(RateRepository.class);
        DiscountRepository discountRepository = mock(DiscountRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(travelTimeCalculator, rateRepository, discountRepository);

        Integer travelTimeInSeconds = 121;
        Integer travelTimeInMinutes =  3;
        Double travelRateValue = 0.2;
        Integer travelDiscountPct = 20;
        Double expectedPrice = 0.48;

        Mockito.when(travelTimeCalculator.getTravelTime()).thenReturn(travelTimeInSeconds);
        Mockito.when(discountRepository.travelDiscount()).thenReturn(travelDiscountPct);
        Mockito.when(discountRepository.rateDiscount()).thenReturn(0);
        Mockito.when(travelTimeCalculator.secondsToMinutes(travelTimeInSeconds)).thenReturn(travelTimeInMinutes);
        Mockito.when(rateRepository.travelRatePerMinute()).thenReturn(travelRateValue);

        Double calculatedPrice = travelPriceCalculator.getPrice();

        Assertions.assertThat(calculatedPrice).isEqualTo(expectedPrice);
    }

    @Test
    void calculate_price_for_121_seconds_travel_with_10_percent_rate_discount () {

        TravelTimeCalculator travelTimeCalculator = mock(TravelTimeCalculator.class);
        RateRepository rateRepository = mock(RateRepository.class);
        DiscountRepository discountRepository = mock(DiscountRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(travelTimeCalculator, rateRepository, discountRepository);

        Integer travelTimeInSeconds = 121;
        Integer travelTimeInMinutes =  3;
        Double travelRateValue = 0.2;
        Integer travelDiscountPct = 0;
        Integer rateDiscountPct = 10;
        Double expectedPrice = 0.54;

        Mockito.when(travelTimeCalculator.getTravelTime()).thenReturn(travelTimeInSeconds);
        Mockito.when(discountRepository.travelDiscount()).thenReturn(travelDiscountPct);
        Mockito.when(discountRepository.rateDiscount()).thenReturn(rateDiscountPct);
        Mockito.when(travelTimeCalculator.secondsToMinutes(travelTimeInSeconds)).thenReturn(travelTimeInMinutes);
        Mockito.when(rateRepository.travelRatePerMinute()).thenReturn(travelRateValue);

        Double calculatedPrice = travelPriceCalculator.getPrice();

        Assertions.assertThat(calculatedPrice).isEqualTo(expectedPrice);
    }

    @Test
    void calculate_price_for_121_seconds_travel_with_pass_and_30_percent_pass_rate_discount () {

        TravelTimeCalculator travelTimeCalculator = mock(TravelTimeCalculator.class);
        RateRepository rateRepository = mock(RateRepository.class);
        DiscountRepository discountRepository = mock(DiscountRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(travelTimeCalculator, rateRepository, discountRepository);

        Integer travelTimeInSeconds = 121;
        Integer travelTimeInMinutes =  3;
        Double passRateValue = 0.2;
        Integer passDiscountPct = 10;
        Integer passRateDiscountPct = 30;
        Double expectedPrice = 0.46;

        Mockito.when(travelTimeCalculator.getTravelTime()).thenReturn(travelTimeInSeconds);
        Mockito.when(discountRepository.passDiscount()).thenReturn(passDiscountPct);
        Mockito.when(discountRepository.passRateDiscount()).thenReturn(passRateDiscountPct);
        Mockito.when(travelTimeCalculator.secondsToMinutes(travelTimeInSeconds)).thenReturn(travelTimeInMinutes);
        Mockito.when(rateRepository.passRatePerMinute()).thenReturn(passRateValue);

        Double calculatedPrice = travelPriceCalculator.getPriceWithPass();

        Assertions.assertThat(calculatedPrice).isEqualTo(expectedPrice);
    }

}