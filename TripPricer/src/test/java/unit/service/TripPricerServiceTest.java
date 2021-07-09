package unit.service;

import TripPricer.model.User;
import TripPricer.model.UserPreferences;
import TripPricer.model.UserReward;
import TripPricer.proxy.UserProxy;
import TripPricer.service.TripPricerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class TripPricerServiceTest {

    private TripPricerService tripPricerService;

    private UserProxy userProxy = Mockito.mock(UserProxy.class);

    @Before
    public void beforeEach() {

        tripPricerService = new TripPricerService(userProxy);
    }

    @Test
    public void calculateTripDeals() {

        //GIVEN
        String userName = "userName";
        User user = Mockito.mock(User.class);

        UserPreferences userPreferences = Mockito.mock(UserPreferences.class);

        List<UserReward> userRewardList = new ArrayList<>();

        //WHEN
        Mockito.when(userProxy.getUser(userName)).thenReturn(user);

        Mockito.when(user.getUserRewards()).thenReturn(userRewardList);
        Mockito.when(user.getUserId()).thenReturn(UUID.randomUUID());
        Mockito.when(user.getUserPreferences()).thenReturn(userPreferences);

        Mockito.when(userPreferences.getNumberOfAdults()).thenReturn(1);
        Mockito.when(userPreferences.getNumberOfChildren()).thenReturn(2);
        Mockito.when(userPreferences.getTripDuration()).thenReturn(3);

        tripPricerService.calculateTripDeals(userName);

        //THEN
        Mockito.verify(userProxy, Mockito.times(1)).setTripDeals(Mockito.any(String.class), Mockito.any(List.class));
    }
}