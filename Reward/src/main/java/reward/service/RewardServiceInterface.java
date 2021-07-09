package reward.service;

import java.util.UUID;

public interface RewardServiceInterface {

    /**
     * Get the reward points amount of an attraction
     * @param attractionId : Id of the attraction to find
     * @param userId : Id of the user to find
     * @return The amount of point of the attraction
     */
    public int getRewardPoints(UUID attractionId, UUID userId);

    /**
     * Calculate the rewards list of an user
     */
    public void calculateRewards(String userName);

    /**
     * Calculate the rewards list of all users
     */
    public void calculateRewardsOfAllUSer();
}