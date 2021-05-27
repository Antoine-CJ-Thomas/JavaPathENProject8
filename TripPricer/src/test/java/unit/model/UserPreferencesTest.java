package unit.model;

import TripPricer.model.UserPreferences;
import org.javamoney.moneta.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.money.CurrencyUnit;

@SpringBootTest
public class UserPreferencesTest {

    private UserPreferences userPreferences;

    @Before
    public void beforeEach() {

        userPreferences = new UserPreferences();
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

    @Test
    public void setAndGetCurrency() {

        //GIVEN
        CurrencyUnit currencyUnit = Mockito.mock(CurrencyUnit.class);

        //WHEN
        userPreferences.setCurrency(currencyUnit);

        //THEN
        Assert.assertTrue(userPreferences.getCurrency() == currencyUnit);
    }

    @Test
    public void setAndGetHighPricePoint() {

        //GIVEN
        CurrencyUnit currencyUnit = Mockito.mock(CurrencyUnit.class);
        Money highPricePoint = Money.of(10, currencyUnit);

        //WHEN
        userPreferences.setHighPricePoint(highPricePoint);

        //THEN
        Assert.assertTrue(userPreferences.getHighPricePoint() == highPricePoint);
    }

    @Test
    public void setAndGetLowerPricePoint() {

        //GIVEN
        CurrencyUnit currencyUnit = Mockito.mock(CurrencyUnit.class);
        Money lowerPricePoint = Money.of(1, currencyUnit);

        //WHEN
        userPreferences.setLowerPricePoint(lowerPricePoint);

        //THEN
        Assert.assertTrue(userPreferences.getLowerPricePoint() == lowerPricePoint);
    }
}