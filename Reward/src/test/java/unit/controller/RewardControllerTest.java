package unit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reward.controller.RewardController;
import reward.service.RewardServiceInterface;

import java.util.UUID;

@SpringBootTest
public class RewardControllerTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    private RewardController rewardController;

    private RewardServiceInterface rewardServiceInterface = Mockito.mock(RewardServiceInterface.class);

    @Before
    public void beforeEach() {

        rewardController = new RewardController(rewardServiceInterface);
    }

    @Test
    public void getRewardPoints() throws JsonProcessingException {

        //GIVEN
        UUID userId = UUID.randomUUID();
        UUID attractionId = UUID.randomUUID();

        //WHEN
        Mockito.when(rewardServiceInterface.getRewardPoints(attractionId, userId)).thenReturn(10);

        //THEN
        Assert.assertTrue(rewardController.getRewardPoints(attractionId, userId).equals(objectMapper.writeValueAsString(10)));
    }

    @Test
    public void calculateRewards() {

        //GIVEN
        String userName = "userName";

        //WHEN
        rewardController.calculateRewards(userName);

        //THEN
        Mockito.verify(rewardServiceInterface, Mockito.times(1)).calculateRewards(userName);
    }

    @Test
    public void calculateRewardsOfAllUSer() {

        //GIVEN
        String userName = "userName";

        //WHEN
        rewardController.calculateRewardsOfAllUSer();

        //THEN
        Mockito.verify(rewardServiceInterface, Mockito.times(1)).calculateRewardsOfAllUSer();
    }
}