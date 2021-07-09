package reward.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reward.service.RewardService;
import reward.service.RewardServiceInterface;

import java.util.UUID;

/**
 * This class allows to intercept reward requests
 */
@RestController
public class RewardController {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private RewardServiceInterface rewardServiceInterface;

    @Autowired
    private RewardService rewardService;

    /**
     * Creates a new RewardController
     */
    public RewardController() {
        logger.info("RewardController()");

        rewardServiceInterface = rewardService;
    }

    /**
     * Creates a new RewardController with the specified RewardServiceInterface
     * @param rewardServiceInterface : service that this controller will use
     */
    public RewardController(RewardServiceInterface rewardServiceInterface) {
        logger.info("RewardController(" + rewardServiceInterface + ")");

        this.rewardServiceInterface = rewardServiceInterface;
    }

    /**
     * Intercepts the reward points getting request
     * @param attractionId : Id of the Attraction to use for calculate reward points
     * @param userId : Id of the User to use for calculate reward points
     * @return The reward points amount (JSon)
     */
    @GetMapping("/getRewardPoints")
    public String getRewardPoints(@RequestParam UUID attractionId, @RequestParam UUID userId) throws JsonProcessingException {
        logger.info("getRewardPoints(" + attractionId + "," + userId + ")");

        return objectMapper.writeValueAsString(rewardServiceInterface.getRewardPoints(attractionId, userId));
    }

    /**
     * Intercepts the user rewards calculating request
     * @param userName : Name of the user whose you want to calculate reward list
     */
    @PutMapping("/calculateRewards")
    public void calculateRewards(@RequestParam String userName) {
        logger.info("calculateRewards(" + userName + ")");

        rewardServiceInterface.calculateRewards(userName);
    }

    /**
     * Intercepts the users rewards calculating request
     */
    @PutMapping("/calculateRewardsOfAllUSer")
    public void calculateRewardsOfAllUSer() {
        logger.info("calculateRewardsOfAllUSer()");

        rewardServiceInterface.calculateRewardsOfAllUSer();
    }
}