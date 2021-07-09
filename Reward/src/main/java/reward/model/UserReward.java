package reward.model;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

/**
 * This class stores the data of an user reward
 */
public class UserReward {

	private VisitedLocation visitedLocation;
	private Attraction attraction;
	private int rewardPoints;

	public UserReward() {}

	public UserReward(VisitedLocation visitedLocation, Attraction attraction, int rewardPoints) {

		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
		this.rewardPoints = rewardPoints;
	}

	public VisitedLocation getVisitedLocation() {
		return visitedLocation;
	}

	public void setVisitedLocation(VisitedLocation visitedLocation) {
		this.visitedLocation = visitedLocation;
	}

	public Attraction getAttraction() {
		return attraction;
	}

	public void setAttraction(Attraction attraction) {
		this.attraction = attraction;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
}