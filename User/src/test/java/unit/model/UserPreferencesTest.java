package unit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import user.model.UserPreferences;

@SpringBootTest
public class UserPreferencesTest {

    private UserPreferences userPreferences;

    @Before
    public void beforeEach() {

        userPreferences = new UserPreferences(0, 0, 0, 0, 0);
    }

    @Test
    public void setAndGetTripDuration() {

        //GIVEN
        int tripDuration = 10;

        //WHEN
        userPreferences.setTripDuration(tripDuration);

        //THEN
        Assert.assertTrue(userPreferences.getTripDuration() == tripDuration);
    }

    @Test
    public void setAndGetTicketQuantity() {

        //GIVEN
        int ticketQuantity = 11;

        //WHEN
        userPreferences.setTicketQuantity(ticketQuantity);

        //THEN
        Assert.assertTrue(userPreferences.getTicketQuantity() == ticketQuantity);
    }

    @Test
    public void setAndGetNumberOfAdults() {

        //GIVEN
        int numberOfAdults = 12;

        //WHEN
        userPreferences.setNumberOfAdults(numberOfAdults);

        //THEN
        Assert.assertTrue(userPreferences.getNumberOfAdults() == numberOfAdults);
    }

    @Test
    public void setAndGetNumberOfChildren() {

        //GIVEN
        int numberOfChildren = 13;

        //WHEN
        userPreferences.setNumberOfChildren(numberOfChildren);

        //THEN
        Assert.assertTrue(userPreferences.getNumberOfChildren() == numberOfChildren);
    }

    @Test
    public void setAndGetAttractionProximity() {

        //GIVEN
        int attractionProximity = 14;

        //WHEN
        userPreferences.setAttractionProximity(attractionProximity);

        //THEN
        Assert.assertTrue(userPreferences.getAttractionProximity() == attractionProximity);
    }
}
