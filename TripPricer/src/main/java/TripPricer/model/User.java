package TripPricer.model;

import gpsUtil.location.VisitedLocation;
import tripPricer.Provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * This class stores the data of an user
 */
public class User {

	private UUID userId;
	private String userName;
	private String phoneNumber;
	private String emailAddress;

	private Date latestLocationTimestamp = new Date();
	private UserPreferences userPreferences = new UserPreferences();

	private List<VisitedLocation> visitedLocations = new ArrayList<>();
	private List<UserReward> userRewards = new ArrayList<>();
	private List<Provider> tripDeals = new ArrayList<>();

	public User() {}

	public User(UUID userId, String userName, String phoneNumber, String emailAddress) {

		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getLatestLocationTimestamp() {
		return latestLocationTimestamp;
	}

	public void setLatestLocationTimestamp(Date latestLocationTimestamp) {
		this.latestLocationTimestamp = latestLocationTimestamp;
	}

	public UserPreferences getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(UserPreferences userPreferences) {
		this.userPreferences = userPreferences;
	}

	public List<VisitedLocation> getVisitedLocations() {
		return visitedLocations;
	}

	public void setVisitedLocations(List<VisitedLocation> visitedLocations) {
		this.visitedLocations = visitedLocations;
	}

	public List<UserReward> getUserRewards() {
		return userRewards;
	}

	public void setUserRewards(List<UserReward> userRewards) {
		this.userRewards = userRewards;
	}

	public List<Provider> getTripDeals() {
		return tripDeals;
	}

	public void setTripDeals(List<Provider> tripDeals) {
		this.tripDeals = tripDeals;
	}
}