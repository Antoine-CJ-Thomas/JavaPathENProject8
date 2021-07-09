package unit.model;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserTest {

    private User user;

    @Before
    public void beforeEach() {

        user = new User(null, null, null, null);
    }

    @Test
    public void setAndGetUserId() {

        //GIVEN
        UUID userId = UUID.randomUUID();

        //WHEN
        user.setUserId(userId);

        //THEN
        Assert.assertTrue(user.getUserId() == userId);
    }

    @Test
    public void setAndGetUserName() {

        //GIVEN
        String userName = "userName";

        //WHEN
        user.setUserName(userName);

        //THEN
        Assert.assertTrue(user.getUserName().equals(userName));
    }

    @Test
    public void setAndGetPhoneNumber() {

        //GIVEN
        String phoneNumber = "phoneNumber";

        //WHEN
        user.setPhoneNumber(phoneNumber);

        //THEN
        Assert.assertTrue(user.getPhoneNumber().equals(phoneNumber));
    }

    @Test
    public void setAndGetEmailAddress() {

        //GIVEN
        String emailAddress = "emailAddress";

        //WHEN
        user.setEmailAddress(emailAddress);

        //THEN
        Assert.assertTrue(user.getEmailAddress().equals(emailAddress));
    }

    @Test
    public void setAndGetLatestLocationTimestamp() {

        //GIVEN
        Date latestLocationTimestamp = new Date();

        //WHEN
        user.setLatestLocationTimestamp(latestLocationTimestamp);

        //THEN
        Assert.assertTrue(user.getLatestLocationTimestamp() == latestLocationTimestamp);
    }

    @Test
    public void setAndGetUserPreferences() {

        //GIVEN
        UserPreferences userPreferences = Mockito.mock(UserPreferences.class);

        //WHEN
        user.setUserPreferences(userPreferences);

        //THEN
        Assert.assertTrue(user.getUserPreferences() == userPreferences);
    }

    @Test
    public void setAndGetVisitedLocations() {

        //GIVEN
        List<VisitedLocation> visitedLocations = new ArrayList<VisitedLocation>();

        //WHEN
        user.setVisitedLocations(visitedLocations);

        //THEN
        Assert.assertTrue(user.getVisitedLocations() == visitedLocations);
    }

    @Test
    public void setAndGetUserRewards() {

        //GIVEN
        List<UserReward> userRewards = new ArrayList<UserReward>();

        //WHEN
        user.setUserRewards(userRewards);

        //THEN
        Assert.assertTrue(user.getUserRewards() == userRewards);
    }

    @Test
    public void setAndGetTripDeals() {

        //GIVEN
        List<Provider> tripDeals = new ArrayList<Provider>();

        //WHEN
        user.setTripDeals(tripDeals);

        //THEN
        Assert.assertTrue(user.getTripDeals() == tripDeals);
    }
}