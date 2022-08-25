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

        TravelTimeCalculator mockTravelTimeCalculator = mock(TravelTimeCalculator.class);
        TravelRateRepository mockTravelRateRepository = mock(TravelRateRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(mockTravelTimeCalculator, mockTravelRateRepository);

        Integer testTimeInSeconds = 900;
        Integer testTimeInMinutes =  15;
        Double testTravelRateValue = 0.2;
        Double testPriceExpected = 3.0 ;

        Mockito.when(mockTravelTimeCalculator.getTravelTime()).thenReturn(testTimeInSeconds);
        Mockito.when(mockTravelTimeCalculator.secondsToMinutes(testTimeInSeconds,true)).thenReturn(testTimeInMinutes);
        Mockito.when(mockTravelRateRepository.getTravelRatePerMinute()).thenReturn(testTravelRateValue);

        Double price = travelPriceCalculator.getPrice();

        Assertions.assertThat(price).isEqualTo(testPriceExpected);
    }
    @Test
    void calculate_price_second_case () {

        TravelTimeCalculator mockTravelTimeCalculator = mock(TravelTimeCalculator.class);
        TravelRateRepository mockTravelRateRepository = mock(TravelRateRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(mockTravelTimeCalculator, mockTravelRateRepository);

        Integer testTimeInSeconds = 121;
        Integer testTimeInMinutes =  3;
        Double testTravelRateValue = 0.2;
        Double testPriceExpected = 0.6 ;

        Mockito.when(mockTravelTimeCalculator.getTravelTime()).thenReturn(testTimeInSeconds);
        Mockito.when(mockTravelTimeCalculator.secondsToMinutes(testTimeInSeconds,true)).thenReturn(testTimeInMinutes);
        Mockito.when(mockTravelRateRepository.getTravelRatePerMinute()).thenReturn(testTravelRateValue);

        Double price = travelPriceCalculator.getPrice();

        Assertions.assertThat(price).isEqualTo(testPriceExpected);
    }@Test

    void calculate_price_third_case_times_shorter_than_one_minute () {

        TravelTimeCalculator mockTravelTimeCalculator = mock(TravelTimeCalculator.class);
        TravelRateRepository mockTravelRateRepository = mock(TravelRateRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(mockTravelTimeCalculator, mockTravelRateRepository);

        Integer testTimeInSeconds = 37;
        Integer testTimeInMinutes =  0;
        Double testTravelRateValue = 0.2;
        Double testPriceExpected = 0.0 ;

        Mockito.when(mockTravelTimeCalculator.getTravelTime()).thenReturn(testTimeInSeconds);
        Mockito.when(mockTravelTimeCalculator.secondsToMinutes(testTimeInSeconds,true)).thenReturn(testTimeInMinutes);
        Mockito.when(mockTravelRateRepository.getTravelRatePerMinute()).thenReturn(testTravelRateValue);

        Double price = travelPriceCalculator.getPrice();

        Assertions.assertThat(price).isEqualTo(testPriceExpected);
    }

}