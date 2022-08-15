package sample;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class TravelPriceCalculatorTest {

    @Test
    void seconds_to_minutes_helper_function_test () {
        TravelTimeCalculator travelTimeCalculator = new TravelTimeCalculator();

        Assertions.assertThat(travelTimeCalculator.getTravelTimeMinutes(37, true)).isEqualTo(0);
        Assertions.assertThat(travelTimeCalculator.getTravelTimeMinutes(121, false)).isEqualTo(2);
        Assertions.assertThat(travelTimeCalculator.getTravelTimeMinutes(121, true)).isEqualTo(3);
    }
    @Test
    void calculate_price_first_case () {

        TravelTimeCalculator mockTravelTimeCalculator = mock(TravelTimeCalculator.class);
        TravelRateRepository mockTravelRateRepository = mock(TravelRateRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(mockTravelTimeCalculator, mockTravelRateRepository);

        String mockTravelId = "1234";
        Integer testTimeInSeconds = 900;
        Integer testTimeInMinutes =  15;
        Double testTravelRateValue = 0.2;
        Double testPriceExpected = 3.0 ;

        Mockito.when(mockTravelTimeCalculator.getTravelTime(mockTravelId)).thenReturn(testTimeInSeconds);
        Mockito.when(mockTravelTimeCalculator.getTravelTimeMinutes(testTimeInSeconds,true)).thenReturn(testTimeInMinutes);
        Mockito.when(mockTravelRateRepository.getTravelRatePerMinute(mockTravelId)).thenReturn(testTravelRateValue);

        Double price = travelPriceCalculator.getPrice(mockTravelId);

        Assertions.assertThat(price).isEqualTo(testPriceExpected);
    }
    @Test
    void calculate_price_second_case () {

        TravelTimeCalculator mockTravelTimeCalculator = mock(TravelTimeCalculator.class);
        TravelRateRepository mockTravelRateRepository = mock(TravelRateRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(mockTravelTimeCalculator, mockTravelRateRepository);

        String mockTravelId = "1234";
        Integer testTimeInSeconds = 121;
        Integer testTimeInMinutes =  3;
        Double testTravelRateValue = 0.2;
        Double testPriceExpected = 0.6 ;

        Mockito.when(mockTravelTimeCalculator.getTravelTime(mockTravelId)).thenReturn(testTimeInSeconds);
        Mockito.when(mockTravelTimeCalculator.getTravelTimeMinutes(testTimeInSeconds,true)).thenReturn(testTimeInMinutes);
        Mockito.when(mockTravelRateRepository.getTravelRatePerMinute(mockTravelId)).thenReturn(testTravelRateValue);

        Double price = travelPriceCalculator.getPrice(mockTravelId);

        Assertions.assertThat(price).isEqualTo(testPriceExpected);
    }@Test

    void calculate_price_third_case_times_shorter_than_one_minute () {

        TravelTimeCalculator mockTravelTimeCalculator = mock(TravelTimeCalculator.class);
        TravelRateRepository mockTravelRateRepository = mock(TravelRateRepository.class);

        TravelPriceCalculator travelPriceCalculator = new TravelPriceCalculator(mockTravelTimeCalculator, mockTravelRateRepository);

        String mockTravelId = "1234";
        Integer testTimeInSeconds = 37;
        Integer testTimeInMinutes =  0;
        Double testTravelRateValue = 0.2;
        Double testPriceExpected = 0.0 ;

        Mockito.when(mockTravelTimeCalculator.getTravelTime(mockTravelId)).thenReturn(testTimeInSeconds);
        Mockito.when(mockTravelTimeCalculator.getTravelTimeMinutes(testTimeInSeconds,true)).thenReturn(testTimeInMinutes);
        Mockito.when(mockTravelRateRepository.getTravelRatePerMinute(mockTravelId)).thenReturn(testTravelRateValue);

        Double price = travelPriceCalculator.getPrice(mockTravelId);

        Assertions.assertThat(price).isEqualTo(testPriceExpected);
    }

}