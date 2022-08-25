package sample;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class TravelPriceCalculatorTest {

    @Test
    void seconds_to_minutes_helper_function_test () {
        TravelTimeCalculator travelTimeCalculator = new TravelTimeCalculator();

        Assertions.assertThat(travelTimeCalculator.secondsToMinutes(37, true)).isEqualTo(0);
        Assertions.assertThat(travelTimeCalculator.secondsToMinutes(121, false)).isEqualTo(2);
        Assertions.assertThat(travelTimeCalculator.secondsToMinutes(121, true)).isEqualTo(3);
    }
    @Test
    void calculate_price_for_900_second_travel_at_20cents_per_minute_rate () {

        TravelTimeCalculator travelTimeCalculator = mock(TravelTimeCalculator.class);
        TravelRateRepository travelRateRepository = mock(TravelRateRepository.class);
        TravelDiscountRepository discountRepository = mock(TravelDiscountRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(travelTimeCalculator, travelRateRepository, discountRepository);

        Integer travelTimeInSeconds = 900;
        Integer travelTimeInMinutes =  15;
        Double travelRateValue = 0.2;
        Double expectedPrice = 3.0 ;

        Mockito.when(travelTimeCalculator.getTravelTime()).thenReturn(travelTimeInSeconds);
        Mockito.when(travelTimeCalculator.secondsToMinutes(travelTimeInSeconds,true)).thenReturn(travelTimeInMinutes);
        Mockito.when(travelRateRepository.getTravelRatePerMinute()).thenReturn(travelRateValue);

        Double calculatedPrice = travelPriceCalculator.getPrice();

        Assertions.assertThat(calculatedPrice).isEqualTo(expectedPrice);
    }
    @Test
    void calculate_price_for_121_seconds_travel_to_be_rounded_to_3_minutes () {

        TravelTimeCalculator travelTimeCalculator = mock(TravelTimeCalculator.class);
        TravelRateRepository travelRateRepository = mock(TravelRateRepository.class);
        TravelDiscountRepository discountRepository = mock(TravelDiscountRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(travelTimeCalculator, travelRateRepository, discountRepository);

        Integer travelTimeInSeconds = 121;
        Integer travelTimeInMinutes =  3;
        Double travelTravelRateValue = 0.2;
        Double expectedPrice = 0.6 ;

        Mockito.when(travelTimeCalculator.getTravelTime()).thenReturn(travelTimeInSeconds);
        Mockito.when(travelTimeCalculator.secondsToMinutes(travelTimeInSeconds,true)).thenReturn(travelTimeInMinutes);
        Mockito.when(travelRateRepository.getTravelRatePerMinute()).thenReturn(travelTravelRateValue);

        Double calculatedPrice = travelPriceCalculator.getPrice();

        Assertions.assertThat(calculatedPrice).isEqualTo(expectedPrice);
    }
    @Test
    void calculate_price_for_travel_times_shorter_than_one_minute_should_be_free () {

        TravelTimeCalculator travelTimeCalculator = mock(TravelTimeCalculator.class);
        TravelRateRepository travelRateRepository = mock(TravelRateRepository.class);
        TravelDiscountRepository discountRepository = mock(TravelDiscountRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(travelTimeCalculator, travelRateRepository, discountRepository);

        Integer travelTimeInSeconds = 37;
        Integer travelTimeInMinutes =  0;
        Double travelRateValue = 0.2;
        Double expectedPrice = 0.0 ;

        Mockito.when(travelTimeCalculator.getTravelTime()).thenReturn(travelTimeInSeconds);
        Mockito.when(travelTimeCalculator.secondsToMinutes(travelTimeInSeconds,true)).thenReturn(travelTimeInMinutes);
        Mockito.when(travelRateRepository.getTravelRatePerMinute()).thenReturn(travelRateValue);

        Double calculatedPrice = travelPriceCalculator.getPrice();

        Assertions.assertThat(calculatedPrice).isEqualTo(expectedPrice);
    }

    @Test
    void calculate_price_for_121_seconds_travel_with_20pct_discount () {

        TravelTimeCalculator travelTimeCalculator = mock(TravelTimeCalculator.class);
        TravelRateRepository travelRateRepository = mock(TravelRateRepository.class);
        TravelDiscountRepository discountRepository = mock(TravelDiscountRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(travelTimeCalculator, travelRateRepository, discountRepository);

        Integer travelTimeInSeconds = 121;
        Integer travelTimeInMinutes =  3;
        Double travelRateValue = 0.2;
        Integer travelDiscountPct = 20;
        Double expectedPrice = 0.48;

        Mockito.when(travelTimeCalculator.getTravelTime()).thenReturn(travelTimeInSeconds);
        Mockito.when(discountRepository.getTravelDiscount()).thenReturn(travelDiscountPct);
        Mockito.when(travelTimeCalculator.secondsToMinutes(travelTimeInSeconds,true)).thenReturn(travelTimeInMinutes);
        Mockito.when(travelRateRepository.getTravelRatePerMinute()).thenReturn(travelRateValue);

        Double calculatedPrice = travelPriceCalculator.getPrice();

        Assertions.assertThat(calculatedPrice).isEqualTo(expectedPrice);
    }

}