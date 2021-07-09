package integration;

import gps.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GpsControllerIT {

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
    public void getUserLocation() throws Exception {

        // GIVEN
        String userName = "userNameTest";

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getUserLocation")
                .param("userName", userName)).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(2)
    public void getAllCurrentLocations() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getAllCurrentLocations")).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(3)
    public void getNearbyAttractions() throws Exception {

        // GIVEN
        String userName = "userNameTest";

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getNearbyAttractions")
                .param("userName", userName)).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(4)
    public void getAttraction() throws Exception {

        // GIVEN
        String attractionName = "attractionNameTest";

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getAttraction")
                .param("attractionName", attractionName)).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(5)
    public void getAttractionList() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getAttractionList")).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(6)
    public void calculateAllUSerLocation() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/calculateAllUSerLocation")).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }
}