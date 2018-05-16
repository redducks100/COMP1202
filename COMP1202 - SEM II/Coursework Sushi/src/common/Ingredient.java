/*
 * @author Andronache Simone
 */
package common;

public class Ingredient extends Model implements Restockable {

	/** The unit. */
	private String unit;
	
	/** The supplier. */
	private Supplier supplier;
	
	/** The restock threshold. */
	private Integer restockThreshold;
	
	/** The restock amount. */
	private Integer restockAmount;
	
	/**
	 * Instantiates a new ingredient.
	 *
	 * @param name the name
	 * @param unit the unit
	 * @param supplier the supplier
	 * @param restockThreshold the restock threshold
	 * @param restockAmount the restock amount
	 */
	public Ingredient(String name, String unit, Supplier supplier, Integer restockThreshold, Integer restockAmount)
	{
		this.name = name;
		this.unit = unit;
		this.supplier = supplier;
		this.restockThreshold = restockThreshold;
		this.restockAmount = restockAmount;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the unit.
	 *
	 * @return the unit
	 */
	public String getUnit()
	{
		return this.unit;
	}
	
	/**
	 * Sets the unit.
	 *
	 * @param unit the new unit
	 */
	public void setUnit(String unit)
	{
		this.notifyUpdate("unit", this.unit, unit);
		this.unit = unit;
	}
	
	/**
	 * Gets the supplier.
	 *
	 * @return the supplier
	 */
	public Supplier getSupplier()
	{
		return this.supplier;
	}
	
	/**
	 * Sets the supplier.
	 *
	 * @param supplier the new supplier
	 */
	public void setSupplier(Supplier supplier)
	{
		this.notifyUpdate("supplier", this.supplier, supplier);
		this.supplier = supplier;
	}

	@Override
	public Integer getRestockThreshold() {
		return this.restockThreshold;
	}

	@Override
	public void setRestockThreshold(Integer restockThreshold) {
		this.notifyUpdate("restockThreshold", this.restockThreshold, restockThreshold);
		this.restockThreshold = restockThreshold;
	}

	@Override
	public Integer getRestockAmount() {
		return this.restockAmount;
	}

	@Override
	public void setRestockAmount(Integer restockAmount) {
		this.notifyUpdate("restockAmount", this.restockAmount, restockAmount);
		this.restockAmount = restockAmount;
	}
	
	/**
	 * Get the object data in string format.
	 *
	 * @return the string
	 */
	public String toPersistanceString()
	{
		String newString = new String("INGREDIENT:");
		newString += this.getName() + ":";
		newString += this.getUnit() + ":";
		newString += this.getSupplier().getName() + ":";
		newString += this.getRestockThreshold().toString() + ":";
		newString += this.getRestockAmount().toString();
		return newString;
	}

}
