package gps.model;

/**
 * This class stores the data of an user preferences
 */
public class UserPreferences {

	private int tripDuration = 1;
	private int ticketQuantity = 1;
	private int numberOfAdults = 1;
	private int numberOfChildren = 0;
	private int attractionProximity = Integer.MAX_VALUE;

	public UserPreferences() {}

	public UserPreferences(int tripDuration, int ticketQuantity, int numberOfAdults, int numberOfChildren, int attractionProximity) {

		this.tripDuration = tripDuration;
		this.ticketQuantity = ticketQuantity;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
		this.attractionProximity = attractionProximity;
	}

	public int getTripDuration() {
		return tripDuration;
	}

	public void setTripDuration(int tripDuration) {
		this.tripDuration = tripDuration;
	}

	public int getTicketQuantity() {
		return ticketQuantity;
	}

	public void setTicketQuantity(int ticketQuantity) {
		this.ticketQuantity = ticketQuantity;
	}

	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public int getAttractionProximity() {
		return attractionProximity;
	}

	public void setAttractionProximity(int attractionProximity) {
		this.attractionProximity = attractionProximity;
	}
}