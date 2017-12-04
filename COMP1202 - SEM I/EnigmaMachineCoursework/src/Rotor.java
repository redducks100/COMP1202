public abstract class Rotor {
	
	protected String name;
	protected int position;	
	protected final int ROTORSIZE = 26; //the maximum size of the mapping array (26 letters in the alphabet
	protected int[] mapping;
	
	/**
	 * Set method for the <i>position</i> variable.
	 * @param position the current position of the rotor
	 */
	public void setPosition(int position)
	{
		this.position = position;
	}
	
	/**
	 * Set method for the <i>position</i> variable.
	 * @return the current position of the rotor
	 */
	public int getPosition()
	{
		return position;
	}
	/**
	 * Initialise the current rotor with the corresponding settings for the passed type.
	 * @param name the type of the rotor
	 * @throws Exception if the type does not exist it will throw an exception
	 */
	public abstract void initialise(String name) throws Exception;
	/**
	 * Substitute the index based on the current <i>position</i> and <i>mapping</i> of the rotor
	 * @param index the integer to substitute
	 * @return the substituted index
	 */
	public abstract int substitute(int index); 
}
