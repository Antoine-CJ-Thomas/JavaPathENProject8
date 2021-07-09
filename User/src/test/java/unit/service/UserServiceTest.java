package unit.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tripPricer.Provider;
import user.model.User;
import user.model.UserPreferences;
import user.model.UserReward;
import user.repository.UserRepositoryInterface;
import user.service.UserService;

import java.util.*;

@SpringBootTest
public class UserServiceTest {

    private UserService userService;

    private UserRepositoryInterface userRepositoryInterface = Mockito.mock(UserRepositoryInterface.class);

    @Before
    public void beforeEach() {

        userService = new UserService(userRepositoryInterface);
    }

    @Test
    public void addUser() {

        //GIVEN
        User user = Mockito.mock(User.class);

        //WHEN
        userService.addUser(user);

        //THEN
        Mockito.verify(userRepositoryInterface, Mockito.times(1)).addUser(user);
    }

    @Test
    public void addToVisitedLocations() {

        //GIVEN
        String userName = "userNameTest";
        User user = Mockito.mock(User.class);

        List<VisitedLocation> visitedLocationList = new ArrayList<>();
        VisitedLocation visitedLocation = Mockito.mock(VisitedLocation.class);

        //WHEN
        Mockito.when(userRepositoryInterface.getUser(userName)).thenReturn(user);
        Mockito.when(user.getVisitedLocations()).thenReturn(visitedLocationList);

        userService.addToVisitedLocations(userName, visitedLocation);

        //THEN
        Assert.assertTrue(visitedLocationList.contains(visitedLocation));
  }

    @Test
    public void addUserReward_thatDoesNotExist() {

        //GIVEN
        String userName = "userNameTest";
        User user = Mockito.mock(User.class);

        UserReward userReward = Mockito.mock(UserReward.class);
        List<UserReward> userRewardList = new ArrayList<>();

        //WHEN
        Mockito.when(userRepositoryInterface.getUser(userName)).thenReturn(user);
        Mockito.when(user.getUserRewards()).thenReturn(userRewardList);

        userService.addUserReward(userName, userReward);

        //THEN
        Assert.assertTrue(userRewardList.contains(userReward));
    }

    @Test
    public void addUserReward_thatAlreadyExist() {

        //GIVEN
        String userName = "userNameTest";
        String attractionName = "attractionNameTest";
        User user = Mockito.mock(User.class);

        UserReward userReward = Mockito.mock(UserReward.class);
        Attraction attraction = Mockito.mock(Attraction.class);
        List<UserReward> userRewardList = new ArrayList<>();

        //WHEN
        Mockito.when(userRepositoryInterface.getUser(userName)).thenReturn(user);
        Mockito.when(user.getUserRewards()).thenReturn(userRewardList);
        Mockito.when(userReward.getAttraction()).thenReturn(attraction);
        Mockito.when(attraction.getAttractionName()).thenReturn(attractionName);

        userRewardList.add(userReward);

        userService.addUserReward(userName, userReward);

        //THEN
        Assert.assertTrue(userRewardList.contains(userReward));
    }

    @Test
    public void setUserPreferences() {

        //GIVEN
        String userName = "userNameTest";
        User user = Mockito.mock(User.class);
        UserPreferences userPreferences = Mockito.mock(UserPreferences.class);

        //WHEN
        Mockito.when(userRepositoryInterface.getUser(userName)).thenReturn(user);

        userService.setUserPreferences(userName, userPreferences);

        //THEN
        Mockito.verify(user, Mockito.times(1)).setUserPreferences(userPreferences);
    }

    @Test
    public void setTripDeals() {

        //GIVEN
        String userName = "userNameTest";
        User user = Mockito.mock(User.class);
        ArrayList<Provider> tripDeals = Mockito.mock(ArrayList.class);

        //WHEN
        Mockito.when(userRepositoryInterface.getUser(userName)).thenReturn(user);

        userService.setTripDeals(userName, tripDeals);

        //THEN
        Mockito.verify(user, Mockito.times(1)).setTripDeals(tripDeals);
    }

    @Test
    public void getUser() {

        //GIVEN
        String userName = "userNameTest";
        User user = Mockito.mock(User.class);

        //WHEN
        Mockito.when(userRepositoryInterface.getUser(userName)).thenReturn(user);

        //THEN
        Assert.assertSame(userService.getUser(userName), user);
    }

    @Test
    public void getAllUser() {

        //GIVEN
        String userName = "userNameTest";

        List<User> userList = new ArrayList<>();

        //WHEN
        Mockito.when(userRepositoryInterface.getAllUser()).thenReturn(userList);

        //THEN
        Assert.assertSame(userService.getAllUser(), userList);
    }
}