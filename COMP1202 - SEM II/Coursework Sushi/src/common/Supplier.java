/*
 * @author Andronache Simone
 */
package common;

public class Supplier extends Model {
	
	/** The distance. */
	private Integer distance;
	
	/**
	 * Instantiates a new supplier.
	 *
	 * @param name the name
	 * @param distance the distance
	 */
	public Supplier(String name, Integer distance)
	{
		this.name = name;
		this.distance = distance;
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
	public Integer getDistance()
	{
		return this.distance;
	}
	
	/**
	 * Sets the distance.
	 *
	 * @param distance the new distance
	 */
	public void setDistance(Integer distance)
	{
		this.notifyUpdate("distance", this.distance, distance);
		this.distance = distance;
	}
	
	/**
	 * Get the object data in string format.
	 *
	 * @return the string
	 */
	public String toPersistanceString()
	{
		String newString = new String("SUPPLIER:");
		newString += this.getName() + ":";
		newString += this.getDistance().toString();
		return newString;
	}

}
