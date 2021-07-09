package TripPricer.service;

/**
 * This interface allows to implement trip pricer service methods
 */
public interface TripPricerServiceInterface {

    /**
     * Calculate the trip deals of an user
     * @param userName : Name of the User to find
     */
    public void calculateTripDeals(String userName);
}