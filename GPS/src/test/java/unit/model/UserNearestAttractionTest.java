package unit.model;

import gps.model.UserNearestAttraction;
import gpsUtil.location.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserNearestAttractionTest {

    private UserNearestAttraction userNearestAttraction;

    @Before
    public void beforeEach() {

        userNearestAttraction = new UserNearestAttraction(null, null, null, 0.0, 0);
    }

    @Test
    public void setAndGetAttractionName() {

        //GIVEN
        String attractionName = "attractionName";

        //WHEN
        userNearestAttraction.setAttractionName(attractionName);

        //THEN
        Assert.assertTrue(userNearestAttraction.getAttractionName().equals(attractionName));
    }

    @Test
    public void setAndGetAttractionLocation() {

        //GIVEN
        Location attractionLocation = Mockito.mock(Location.class);

        //WHEN
        userNearestAttraction.setAttractionLocation(attractionLocation);

        //THEN
        Assert.assertTrue(userNearestAttraction.getAttractionLocation() == attractionLocation);
    }

    @Test
    public void setAndGetUserLocation() {

        //GIVEN
        Location userLocation = Mockito.mock(Location.class);

        //WHEN
        userNearestAttraction.setUserLocation(userLocation);

        //THEN
        Assert.assertTrue(userNearestAttraction.getUserLocation() == userLocation);
    }

    @Test
    public void setAndGetAttractionMilesDistance() {

        //GIVEN
        double attractionMilesDistance = 10;

        //WHEN
        userNearestAttraction.setAttractionMilesDistance(attractionMilesDistance);

        //THEN
        Assert.assertTrue(userNearestAttraction.getAttractionMilesDistance() == attractionMilesDistance);
    }

    @Test
    public void setAndGetRewardPoints() {

        //GIVEN
        int rewardPoints = 20;

        //WHEN
        userNearestAttraction.setRewardPoints(rewardPoints);

        //THEN
        Assert.assertTrue(userNearestAttraction.getRewardPoints() == rewardPoints);
    }
}