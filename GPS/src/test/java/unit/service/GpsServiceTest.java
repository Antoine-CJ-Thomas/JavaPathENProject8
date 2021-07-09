package unit.service;

import gps.model.User;
import gps.proxy.RewardProxy;
import gps.proxy.UserProxy;
import gps.service.GpsService;
import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class GpsServiceTest {

    private GpsService gpsService;

    private UserProxy userProxy = Mockito.mock(UserProxy.class);
    private RewardProxy rewardProxy = Mockito.mock(RewardProxy.class);

    @Before
    public void beforeEach() {

        Locale.setDefault(Locale.US);

        gpsService = new GpsService(userProxy, rewardProxy);
    }

    @Test
    public void getUserLocation() {

        //GIVEN
        String userName = "userNameTest";
        User user = Mockito.mock(User.class);
        VisitedLocation visitedLocation = Mockito.mock(VisitedLocation.class);

        List<VisitedLocation> visitedLocationList = new ArrayList<>();

        //WHEN
        Mockito.when(userProxy.getUser(userName)).thenReturn(user);
        Mockito.when(user.getUserId()).thenReturn(UUID.randomUUID());
        Mockito.when(user.getVisitedLocations()).thenReturn(visitedLocationList);

        visitedLocationList.add(visitedLocation);

        //THEN
        Assert.assertSame(gpsService.getUserLocation(userName), visitedLocation);
    }

    @Test
    public void getAttraction() {

        //GIVEN
        String attractionName = "Disneyland";

        //WHEN

        //THEN
        Assert.assertTrue(gpsService.getAttraction(attractionName).getAttractionName().equals(attractionName));
    }

    @Test
    public void getAllAttraction() {

        //GIVEN
        GpsUtil gpsUtil = new GpsUtil();

        Attraction attraction = gpsUtil.getAttractions().get(0);
        Attraction attractionReturned = gpsService.getAttractionList().get(0);

        //WHEN

        //THEN
        Assert.assertEquals(attraction.getAttractionName(), attractionReturned.getAttractionName());
    }

    @Test
    public void getAllCurrentLocations() {

        //GIVEN
        UUID userId = UUID.randomUUID();

        User user = Mockito.mock(User.class);
        List<User> userList = new ArrayList<>();

        VisitedLocation visitedLocation = Mockito.mock(VisitedLocation.class);
        List<VisitedLocation> visitedLocationList = new ArrayList<>();

        //WHEN
        Mockito.when(userProxy.getAllUser()).thenReturn(userList);
        userList.add(user);

        Mockito.when(user.getUserId()).thenReturn(userId);
        Mockito.when(user.getVisitedLocations()).thenReturn(visitedLocationList);
        visitedLocationList.add(visitedLocation);

        //THEN
        Assert.assertSame(gpsService.getAllCurrentLocations().get(userId), visitedLocation);
    }

    @Test
    public void calculateAllUSerLocation() {

        //GIVEN
        String username = "usernameTest";
        User user = Mockito.mock(User.class);
        List<User> userList = new ArrayList<>();

        //WHEN
        Mockito.when(userProxy.getAllUser()).thenReturn(userList);
        Mockito.when(user.getUserName()).thenReturn(username);
        userList.add(user);

        gpsService.calculateAllUSerLocation();

        //THEN
        Mockito.verify(userProxy, Mockito.times(1)).addToVisitedLocations(Mockito.any(String.class), Mockito.any(VisitedLocation.class));
    }
}