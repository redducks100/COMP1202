public class TurnoverRotor extends BasicRotor {
	
	private int turnoverPosition;
	private Rotor nextRotor;
	
	public TurnoverRotor(String name) throws Exception {
		super(name);
		setTurnoverPosition(name);
	}

	public TurnoverRotor(String name, BasicRotor nextRotor) throws Exception
	{
		super(name);
		setTurnoverPosition(name);
		this.nextRotor = nextRotor;
	}
	
	/**
	 * Initialise the <i>turnoverPosition</i> variable corresponding to the given type.
	 * @param name the type of the rotor 
	 */
	private void setTurnoverPosition(String name)
	{
		switch(name)
		{
			case "I":
				turnoverPosition = 24;
				break;
			case "II":
				turnoverPosition = 12;
				break;
			case "III":
				turnoverPosition = 3;
				break;
			case "IV":
				turnoverPosition = 17;
				break;
			case "V":
				turnoverPosition = 7;
				break;
		}
	}
	
	/**
	 * Set method for the nextRotor variable
	 * @param nextRotor a Rotor object 
	 */
	public void setNextRotor(Rotor nextRotor)
	{
		this.nextRotor = nextRotor;
	}
	
	
	/**
	 * Rotate the rotor by +1 and rotate the next <i>TurnoverRotor</i> if it exists.
	 * It will not overflow.
	 */
	@Override
	public void rotate()
	{
		super.rotate();
		
		if(position == turnoverPosition && nextRotor != null)
		{
			((BasicRotor)nextRotor).rotate();
		}
	}
}
