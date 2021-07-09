package gps.model;

import gpsUtil.location.Location;

/**
 * This class stores the data of an user nearest attraction
 */
public class UserNearestAttraction {

    private String attractionName;
    private Location attractionLocation;
    private Location userLocation;
    private double attractionMilesDistance;
    private int rewardPoints;

    public UserNearestAttraction() {}

    public UserNearestAttraction(String attractionName, Location attractionLocation, Location userLocation, double attractionMilesDistance, int rewardPoints) {

        this.attractionName = attractionName;
        this.attractionLocation = attractionLocation;
        this.userLocation = userLocation;
        this.attractionMilesDistance = attractionMilesDistance;
        this.rewardPoints = rewardPoints;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public Location getAttractionLocation() {
        return attractionLocation;
    }

    public void setAttractionLocation(Location attractionLocation) {
        this.attractionLocation = attractionLocation;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }

    public double getAttractionMilesDistance() {
        return attractionMilesDistance;
    }

    public void setAttractionMilesDistance(double attractionMilesDistance) {
        this.attractionMilesDistance = attractionMilesDistance;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}