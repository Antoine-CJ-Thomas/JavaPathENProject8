package unit.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reward.model.User;
import reward.model.UserReward;
import reward.proxy.GpsProxy;
import reward.proxy.UserProxy;
import reward.service.RewardService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class RewardServiceTest {

    private RewardService rewardService;

    private UserProxy userProxy = Mockito.mock(UserProxy.class);
    private GpsProxy gpsProxy = Mockito.mock(GpsProxy.class);

    @Before
    public void beforeEach() {

        rewardService = new RewardService(userProxy, gpsProxy);
    }

    @Test
    public void getRewardPoints() {

        //GIVEN
        UUID userId = UUID.randomUUID();
        UUID attractionId = UUID.randomUUID();

        //WHEN

        //THEN
        Assert.assertTrue(rewardService.getRewardPoints(attractionId, userId) >= 0);
    }

    @Test
    public void calculateRewards() {

        //GIVEN
        String username = "usernameTest";
        User user = Mockito.mock(User.class);

        Attraction attraction = Mockito.mock(Attraction.class);
        List<Attraction> attractionList = new ArrayList<>();

        UserReward userReward = Mockito.mock(UserReward.class);
        Attraction rewardAttraction = Mockito.mock(Attraction.class);
        List<UserReward> userRewardList = new ArrayList<>();

        VisitedLocation visitedLocation = Mockito.mock(VisitedLocation.class);
        Location visitedLocationLocation = Mockito.mock(Location.class);
        List<VisitedLocation> visitedLocationList = new ArrayList<>();

        //WHEN
        Mockito.when(userProxy.getUser(username)).thenReturn(user);
        Mockito.when(user.getUserName()).thenReturn(username);

        Mockito.when(gpsProxy.getAllAttraction()).thenReturn(attractionList);
        Mockito.when(attraction.getAttractionName()).thenReturn("attractionNameTest");
        Mockito.when(attraction.getLongitude()).thenReturn(2.0);
        Mockito.when(attraction.getLatitude()).thenReturn(1.0);
        attractionList.add(attraction);

        Mockito.when(user.getUserRewards()).thenReturn(userRewardList);
        Mockito.when(userReward.getAttraction()).thenReturn(rewardAttraction);
        Mockito.when(rewardAttraction.getAttractionName()).thenReturn("rewardAttractionNameTest");
        userRewardList.add(userReward);

        Mockito.when(user.getVisitedLocations()).thenReturn(visitedLocationList);
        Mockito.when(visitedLocation.getLocation()).thenReturn(visitedLocationLocation);
        Mockito.when(visitedLocationLocation.getLongitude()).thenReturn(2.0);
        Mockito.when(visitedLocationLocation.getLatitude()).thenReturn(1.0);
        visitedLocationList.add(visitedLocation);

        rewardService.calculateRewards(username);

        //THEN
        Mockito.verify(userProxy, Mockito.times(1)).addUserReward(Mockito.any(String.class), Mockito.any(UserReward.class));
    }

    @Test
    public void calculateRewardsOfAllUSer() {

        //GIVEN
        String username = "usernameTest";
        User user = Mockito.mock(User.class);
        List<User> userList = new ArrayList<>();

        Attraction attraction = Mockito.mock(Attraction.class);
        List<Attraction> attractionList = new ArrayList<>();

        UserReward userReward = Mockito.mock(UserReward.class);
        Attraction rewardAttraction = Mockito.mock(Attraction.class);
        List<UserReward> userRewardList = new ArrayList<>();

        VisitedLocation visitedLocation = Mockito.mock(VisitedLocation.class);
        Location visitedLocationLocation = Mockito.mock(Location.class);
        List<VisitedLocation> visitedLocationList = new ArrayList<>();

        //WHEN
        Mockito.when(userProxy.getAllUser()).thenReturn(userList);
        Mockito.when(user.getUserName()).thenReturn(username);
        userList.add(user);

        Mockito.when(gpsProxy.getAllAttraction()).thenReturn(attractionList);
        Mockito.when(attraction.getAttractionName()).thenReturn("attractionNameTest");
        Mockito.when(attraction.getLongitude()).thenReturn(2.0);
        Mockito.when(attraction.getLatitude()).thenReturn(1.0);
        attractionList.add(attraction);

        Mockito.when(user.getUserRewards()).thenReturn(userRewardList);
        Mockito.when(userReward.getAttraction()).thenReturn(rewardAttraction);
        Mockito.when(rewardAttraction.getAttractionName()).thenReturn("rewardAttractionNameTest");
        userRewardList.add(userReward);

        Mockito.when(user.getVisitedLocations()).thenReturn(visitedLocationList);
        Mockito.when(visitedLocation.getLocation()).thenReturn(visitedLocationLocation);
        Mockito.when(visitedLocationLocation.getLongitude()).thenReturn(2.0);
        Mockito.when(visitedLocationLocation.getLatitude()).thenReturn(1.0);
        visitedLocationList.add(visitedLocation);

        rewardService.calculateRewardsOfAllUSer();

        //THEN
        Mockito.verify(userProxy, Mockito.times(1)).addUserReward(Mockito.any(String.class), Mockito.any(UserReward.class));
    }
}
