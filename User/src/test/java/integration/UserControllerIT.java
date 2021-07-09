package integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tripPricer.Provider;
import user.Application;
import user.model.User;
import user.model.UserPreferences;
import user.model.UserReward;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerIT {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;

    @Before
    public void beforeEach() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void addUser() throws Exception {

        // GIVEN
        User user = new User(UUID.randomUUID(), "userNameTest", "phoneNumberTest", "emailAddressTest");

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(2)
    public void addToVisitedLocations() throws Exception {

        // GIVEN
        String userName = "userNameTest";
        Location location = new Location(1.1, 2.2);
        VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(), location, new Date());

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/addToVisitedLocations")
                .param("userName", userName)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(visitedLocation))).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(3)
    public void addUserReward() throws Exception {

        // GIVEN
        String userName = "userNameTest";
        Location location = new Location(1.1, 2.2);
        VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(), location, new Date());
        Attraction attraction = new Attraction("attractionNameTest", "cityTest", "stateTest", 11.1, 22.2);
        UserReward userReward = new UserReward(visitedLocation, attraction, 10);

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/addUserReward")
                .param("userName", userName)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userReward))).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(4)
    public void setUserPreferences() throws Exception {

        // GIVEN
        String userName = "userNameTest";
        UserPreferences userPreferences = new UserPreferences(1, 2, 3 ,4 ,5);

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/setUserPreferences")
                .param("userName", userName)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userPreferences))).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(4)
    public void setTripDeals() throws Exception {

        // GIVEN
        String userName = "userNameTest";
        Provider provider = new Provider(UUID.randomUUID(), "name", 20.0);
        ArrayList<Provider> providerList = new ArrayList<>();

        // WHEN
        providerList.add(provider);

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/setTripDeals")
                .param("userName", userName)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(providerList))).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(6)
    public void getUser() throws Exception {

        // GIVEN
        String userName = "userNameTest";

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getUser")
                .param("userName", userName)).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(7)
    public void getAllUser() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getAllUser")).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }
}