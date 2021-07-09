package performance;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reward.Application;
import reward.model.User;
import reward.model.UserReward;
import reward.proxy.GpsProxy;
import reward.proxy.UserProxy;
import reward.service.RewardService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RewardControllerPerformance {

    private UserProxy mockedUserProxy = Mockito.mock(UserProxy.class);
    private GpsProxy mockedGpsProxy = Mockito.mock(GpsProxy.class);

    @Autowired
    private GpsProxy gpsProxy;

    @Test
    public void highVolumeTrackLocation() {

        int userNumber = 100;
        StopWatch stopWatch = new StopWatch();
        RewardService rewardService = new RewardService(mockedUserProxy , mockedGpsProxy);

        List<User> userList = new ArrayList<>();

        List<VisitedLocation> visitedLocationList = new ArrayList<>();
        VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(33.817595D, -117.922008D), new Date());

        List<UserReward> userRewardList = new ArrayList<>();
        UserReward userReward = new UserReward(visitedLocation, gpsProxy.getAllAttraction().get(1), 10);

        visitedLocationList.add(visitedLocation);
        userRewardList.add(userReward);

        for (int i = 1; i <= userNumber; i++) {

            User user = new User(UUID.randomUUID(), ("userNameTest " + i), ("phoneNumberTest " + i), ("emailAddressTest " + i));

            user.setVisitedLocations(visitedLocationList);
            user.setUserRewards(userRewardList);

            userList.add(user);

            System.out.println("User " + i + " added to the list");
        }

        Mockito.when(mockedUserProxy.getAllUser()).thenReturn(userList);
        Mockito.when(mockedGpsProxy.getAllAttraction()).thenReturn(gpsProxy.getAllAttraction());

        stopWatch.start();

        rewardService.calculateRewardsOfAllUSer();

        stopWatch.stop();

        System.out.println("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
        Assert.assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
    }
}