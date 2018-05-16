/*
 * @author Andronache Simone
 */
package common;

public interface Restockable {
	
	/**
	 * Gets the restock threshold.
	 *
	 * @return the restock threshold
	 */
	public Integer getRestockThreshold();
	
	/**
	 * Sets the restock threshold.
	 *
	 * @param restockThreshold the new restock threshold
	 */
	public void setRestockThreshold(Integer restockThreshold);
	
	/**
	 * Gets the restock amount.
	 *
	 * @return the restock amount
	 */
	public Integer getRestockAmount();
	
	/**
	 * Sets the restock amount.
	 *
	 * @param restockAmount the new restock amount
	 */
	public void setRestockAmount(Integer restockAmount);
}
