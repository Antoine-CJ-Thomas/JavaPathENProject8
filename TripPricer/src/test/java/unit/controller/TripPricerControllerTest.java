package unit.controller;

import TripPricer.controller.TripPriceController;
import TripPricer.service.TripPricerServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TripPricerControllerTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    private TripPriceController tripPriceController;

    private TripPricerServiceInterface tripPricerServiceInterface = Mockito.mock(TripPricerServiceInterface.class);

    @Before
    public void beforeEach() {

        tripPriceController = new TripPriceController(tripPricerServiceInterface);
    }

    @Test
    public void calculateTripDeals() {

        //GIVEN
        String userName = "userName";

        //WHEN
        tripPriceController.calculateTripDeals(userName);

        //THEN
        Mockito.verify(tripPricerServiceInterface, Mockito.times(1)).calculateTripDeals(userName);
    }
}