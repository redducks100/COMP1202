public class BasicRotor extends Rotor {

	private int[] inverseMapping; //the inverse mapping of the current type map
	
	public BasicRotor(String name) throws Exception
	{
		this.name = name;
		this.inverseMapping = new int[ROTORSIZE];
		initialise(this.name);
	}
	
	@Override
	public void initialise(String name) throws Exception
	{
		switch(name)
		{
			case "I":
				mapping = new int[] { 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
				break;
			case "II":
				mapping = new int[] { 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 };
				break;
			case "III":
				mapping = new int[] { 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 };
				break;
			case "IV":
				mapping = new int[] { 4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 };
				break;
			case "V":
				mapping = new int[] { 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 };
				break;
			default:
				throw new Exception("Invalid BasicRotor type!");
		}
		
		initialiseInverse();
	}
	
	/**
	 * Initialise the inverse mapping array corresponding with the selected type. 
	 */
	private void initialiseInverse()
	{
		for(int i = 0; i < mapping.length; i++)
		{
			inverseMapping[mapping[i]] = i;
		}
	}
	

	@Override
	public int substitute(int index) 
	{
		if(index - position < 0) //make sure it's not negative
		{
			index = ROTORSIZE + index - position; 
		}
		else
		{
			index = index - position;
		}
		
		return (mapping[index] + position) % ROTORSIZE;
	}
	
	/**
	 * Substitute the index based on the current <i>position</i> and <i>inverseMapping</i> of the rotor
	 * @param index the integer to substitute
	 * @return the substituted index
	 */
	public int substituteBack(int index)
	{
		if(index - position < 0)
		{
			index = ROTORSIZE + index - position;
		}
		else
		{
			index = index - position;
		}
		return (inverseMapping[index] + position) % ROTORSIZE;
	}
	
	/**
	 * Rotate the rotor by +1.
	 * It will not overflow.
	 */
	public void rotate()
	{
		position = (position + 1) % ROTORSIZE;
	}
}
