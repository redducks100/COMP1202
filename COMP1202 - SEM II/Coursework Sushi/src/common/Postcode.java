/*
 * @author Andronache Simone
 */
package common;

public class Postcode extends Model {

	/** The distance. */
	private Number distance;
	
	/**
	 * Instantiates a new postcode.
	 *
	 * @param name the name
	 * @param distance the distance
	 */
	public Postcode(String name, Number distance)
	{
		this.name = name;
		this.setDistance(distance);
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the distance.
	 *
	 * @return the distance
	 */
	public Number getDistance() {
		return this.distance;
	}

	/**
	 * Sets the distance.
	 *
	 * @param distance the new distance
	 */
	public void setDistance(Number distance) {
		this.notifyUpdate("distance", this.distance, distance);
		this.distance = distance;
	}

	/**
	 * Gets the object data in a string format.
	 *
	 * @return the string
	 */
	public String toPersistanceString()
	{
		String newString = new String("POSTCODE:");
		newString += this.getName() + ":";
		newString += this.getDistance().toString();
		return newString;
	}
}
