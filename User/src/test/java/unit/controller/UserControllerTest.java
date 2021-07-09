package unit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gpsUtil.location.VisitedLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tripPricer.Provider;
import user.controller.UserController;
import user.model.User;
import user.model.UserPreferences;
import user.model.UserReward;
import user.service.UserServiceInterface;

import java.util.*;

@SpringBootTest
public class UserControllerTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    private UserController userController;

    private UserServiceInterface userServiceInterface = Mockito.mock(UserServiceInterface.class);

    @Before
    public void beforeEach() {

        userController = new UserController(userServiceInterface);
    }

    @Test
    public void addUser() {

        //GIVEN
        User user = Mockito.mock(User.class);

        //WHEN
        Mockito.when(user.getUserName()).thenReturn("userNameTest");
        Mockito.when(user.getPhoneNumber()).thenReturn("phoneNumberTest");
        Mockito.when(user.getEmailAddress()).thenReturn("emailAddressTest");

        userController.addUser(user);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).addUser(Mockito.any(User.class));
    }

    @Test
    public void addToVisitedLocations() {

        //GIVEN
        String userName = "userNameTest";
        VisitedLocation visitedLocation = Mockito.mock(VisitedLocation.class);

        //WHEN
        userController.addToVisitedLocations(userName, visitedLocation);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).addToVisitedLocations(userName, visitedLocation);
    }

    @Test
    public void addUserReward() {

        //GIVEN
        String userName = "userNameTest";
        UserReward userReward = Mockito.mock(UserReward.class);

        //WHEN
        userController.addUserReward(userName, userReward);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).addUserReward(userName, userReward);
    }

    @Test
    public void setUserPreferences() {

        //GIVEN
        String userName = "userNameTest";
        UserPreferences userPreferences = Mockito.mock(UserPreferences.class);

        //WHEN
        userController.setUserPreferences(userName, userPreferences);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).setUserPreferences(userName, userPreferences);
    }

    @Test
    public void setTripDeals() {

        //GIVEN
        String userName = "userNameTest";
        ArrayList<Provider> tripDeals = Mockito.mock(ArrayList.class);

        //WHEN
        userController.setTripDeals(userName, tripDeals);

        //THEN
        Mockito.verify(userServiceInterface, Mockito.times(1)).setTripDeals(userName, tripDeals);
    }

    @Test
    public void getAllUser() throws JsonProcessingException {

        //GIVEN
        ArrayList<User> userList = Mockito.mock(ArrayList.class);

        //WHEN
        Mockito.when(userServiceInterface.getAllUser()).thenReturn(userList);

        //THEN
        Assert.assertEquals(userController.getAllUser(), objectMapper.writeValueAsString(userList));
    }
}