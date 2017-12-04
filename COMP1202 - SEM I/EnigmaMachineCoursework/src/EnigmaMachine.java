public class EnigmaMachine {
	
	private Plugboard plugboard;
	private Rotor[] rotors = new Rotor[3];
	private Reflector reflector;
	
	private boolean turnoverSetup = false;
	
	public EnigmaMachine()
	{
		plugboard = new Plugboard();
	}
	
	/**
	 * Add a plug to the plugboard.
	 * @param end1 one end of the plug 
	 * @param end2 one end of the plug
	 */
	public void addPlug(char end1, char end2) throws Exception
	{
		String display = "Plug (" + end1 +", " + end2 + ") ";
		
		if(plugboard.addPlug(end1, end2) == false)
		{
			throw new Exception(display + " could not be added (another plug already exists with a common end point).");
		}
	}
	
	/**
	 * Get method for the <i>plugboard</i> object.
	 * @return Plugboard a Plugboard object 
	 **/
	public Plugboard getPlugboard()
	{
		return plugboard;
	}
	
	/**
	 * Clear the current plugboard of plugs.
	 */
	public void clearPlugboard()
	{
		plugboard.clear();
	}
	
	/**
	 * Add a <i>BasicRotor</i> to the specified position in the array.
	 * @param rotor the BasicRotor to add 
	 * @param slot the position of the rotor
	 */
	public void addRotor(BasicRotor rotor, int slot)
	{
		rotors[slot] = rotor;
		turnoverSetup = false;
	}
	
	/**
	 * Get a <i>BasicRotor</i> from the specified position in the array.
	 * @param slot the position of the rotor 
	 * @return Rotor the rotor in that position
	 */
	public Rotor getRotor(int slot)
	{
		return rotors[slot];
	}
	
	/**
	 * Set method for the <i>reflector</i> variable
	 * @param reflector the value of the variable 
	 */
	public void addReflector(Reflector reflector)
	{
		this.reflector = reflector;
	}
	
	/**
	 * Get method for the <i>reflector</i> variable
	 * @return Reflector the value of the variable 
	 */
	public Reflector getReflector()
	{
		return reflector;
	}
	
	/**
	 * Set the position of the rotor in the specified position.  
	 * @param slot the position of the rotor in the array
	 * @param position a position to set the rotor to
	 */
	public void setPosition(int slot, int position)
	{
		rotors[slot].setPosition(position);
	}
	
	/**
	 * Get an array with all the rotor's positions.
	 * @return int[] an integer array
	 */
	public int[] getPositions()
	{
		int[] positions = new int[3];
		
		for(int i=0;i<3;i++)
		{
			positions[i] = rotors[i].getPosition();
		}
		
		return positions;
	}
	
	/**
	 * Set the next rotor for the turnoverRotors if there is any. 
	 */
	private void setupTurnoverRotors()
	{
		for(int i=0; i<2; i++)
		{
			if(rotors[i].getClass() == TurnoverRotor.class)
			{
				((TurnoverRotor)rotors[i]).setNextRotor(rotors[i+1]);
			}
		}
		turnoverSetup = true;
	}
	
	/**
	 * Encode a single character using the current settings of the enigma machine.
	 * @param letter character to be encoded
	 * @return char the encoded character
	 */
	public char encodeLetter(char letter) throws Exception
	{
		//if any of the rotors got modified do the setup AGAIN
		if(turnoverSetup == false)
		{
			setupTurnoverRotors();
		}
		
		if(letter < 'A' || letter > 'Z')
		{
			throw new Exception("Can't encode non-letters characters!");
		}
		
		char encodedLetter = plugboard.substitute(letter);
		int encodedIndex = encodedLetter - 'A';
		
		for(int i=0;i < rotors.length; i++)
		{
			encodedIndex = rotors[i].substitute(encodedIndex);
		}
		
		encodedIndex = reflector.substitute(encodedIndex);
		
		for(int i=rotors.length-1; i >= 0 ; i--)
		{
			encodedIndex = ((BasicRotor)rotors[i]).substituteBack(encodedIndex);
		}
		
		encodedLetter = plugboard.substitute((char) (encodedIndex + 'A'));
		
		((BasicRotor)rotors[0]).rotate();
		
		return encodedLetter;
	}
	
	/**
	 * Encode a string using the current settings of the machine.
	 * @param message a String to be encoded
	 * @return String the encoded string
	 */
	public String encodeString(String message)
	{
		String newString = "";
		
		try {
			for(char c : message.toCharArray())
			{
				newString += encodeLetter(c);
			}
		}
		catch(Exception ex)
		{
			newString = "";
			System.err.println(ex.toString());
		}
		return newString;
	}
	
	public void start() throws Exception
	{
		clearPlugboard();
		addPlug('A','M');
		addPlug('G','L');
		addPlug('E','T');
		addRotor(new BasicRotor("I"), 0);
		addRotor(new BasicRotor("II"), 1);
		addRotor(new BasicRotor("III"), 2);
		
		setPosition(0, 6);
		setPosition(1, 12);
		setPosition(2, 5);
		
		addReflector(new Reflector());
		reflector.initialise("ReflectorI");
		System.out.println("Decoding the following string: GFWIQH");
		System.out.println("Decoded message is: " + encodeString("GFWIQH"));
		System.out.println();
		
		clearPlugboard();
		addPlug('B','C');
		addPlug('R','I');
		addPlug('S','M');
		addPlug('A','F');
		addRotor(new BasicRotor("IV"), 0);
		addRotor(new BasicRotor("V"), 1);
		addRotor(new BasicRotor("II"), 2);
		
		setPosition(0, 23);
		setPosition(1, 4);
		setPosition(2, 9);
		
		addReflector(new Reflector());
		reflector.initialise("ReflectorII");
		System.out.println("Decoding the following string: GACIG");
		System.out.println("Decoded message is: " + encodeString("GACIG"));
	}
}
