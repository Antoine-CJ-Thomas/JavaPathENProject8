package performance;

import gps.Application;
import gps.model.User;
import gps.proxy.RewardProxy;
import gps.proxy.UserProxy;
import gps.service.GpsService;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GpsControllerPerformance {

    private UserProxy mockedUserProxy = Mockito.mock(UserProxy.class);

    private RewardProxy mockedRewardProxy = Mockito.mock(RewardProxy.class);

    @Test
    public void highVolumeTrackLocation() {

        int userNumber = 100;
        StopWatch stopWatch = new StopWatch();
        GpsService gpsService = new GpsService(mockedUserProxy , mockedRewardProxy);

        List<User> userList = new ArrayList<>();

        for (int i = 1; i <= userNumber; i++) {

            User user = new User(UUID.randomUUID(), ("userNameTest " + i), ("phoneNumberTest " + i), ("emailAddressTest " + i));

            userList.add(user);

            System.out.println("User " + i + " added to the list");
        }

        Mockito.when(mockedUserProxy.getAllUser()).thenReturn(userList);

        stopWatch.start();

        gpsService.calculateAllUSerLocation();

        stopWatch.stop();

        System.out.println("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
        Assert.assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
    }
}