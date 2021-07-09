package unit.model;

import gps.model.UserReward;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRewardTest {

    private UserReward userReward;

    @Before
    public void beforeEach() {

        userReward = new UserReward(null, null, 0);
    }

    @Test
    public void setAndGetVisitedLocation() {

        //GIVEN
        VisitedLocation visitedLocation = Mockito.mock(VisitedLocation.class);

        //WHEN
        userReward.setVisitedLocation(visitedLocation);

        //THEN
        Assert.assertTrue(userReward.getVisitedLocation() == visitedLocation);
    }

    @Test
    public void setAndGetAttraction() {

        //GIVEN
        Attraction attraction = Mockito.mock(Attraction.class);

        //WHEN
        userReward.setAttraction(attraction);

        //THEN
        Assert.assertTrue(userReward.getAttraction() == attraction);
    }

    @Test
    public void setAndGetRewardPoints() {

        //GIVEN
        int rewardPoints = 10;

        //WHEN
        userReward.setRewardPoints(rewardPoints);

        //THEN
        Assert.assertTrue(userReward.getRewardPoints() == rewardPoints);
    }
}
