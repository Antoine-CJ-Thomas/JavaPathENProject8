package integration;

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
import reward.Application;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RewardControllerIT {

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
    public void getRewardPoints() throws Exception {

        // GIVEN
        UUID attractionId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getRewardPoints")
                .param("attractionId", String.valueOf(attractionId))
                .param("userId", String.valueOf(userId))).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(2)
    public void calculateRewards() throws Exception {

        // GIVEN
        String userName = "userNameTest";

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/calculateRewards")
                .param("userName", userName)).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Order(3)
    public void calculateRewardsOfAllUSer() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/calculateRewardsOfAllUSer")).andReturn();

        // THEN
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }
}