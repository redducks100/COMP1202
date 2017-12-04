import java.util.ArrayList;

public class Bombe {
	
	private EnigmaMachine machine;
	
	public Bombe(EnigmaMachine machine)
	{
		this.machine = machine;
	}
	
	/**
	 * Get method for the <i>machine</i> variable.
	 * @return the current used machine
	 */
	public EnigmaMachine getMachine()
	{
		return machine;
	}
	
	/**
	 * Set method for the <i>machine</i> variable.
	 * @param machine an EnigmaMachine
	 */
	public void setMachine(EnigmaMachine machine)
	{
		this.machine = machine;
	}
	
	/**
	 * Uses 3 different settings to crack a enigmaMachine given a known word.
	 * @param setting what bombe settings should be used
	 * @param message the string to decrypt
	 * @param word the known word in the decrypted String
	 * @return an arrayList with all the possible decrypted Strings
	 * @throws Exception if settings is null or something is wrong with the enigma machine
	 */
	public ArrayList<String> crackEnigma(BombeSettings setting, String message, String word) throws Exception
	{
		ArrayList<String> possibleDecryptedMessages = new ArrayList<String>();
		
		if(setting == null)
		{
			throw new Exception("The setting can't be null. Choose between the available types.");
		}
		
		switch(setting.getType())
		{
			case MISSING_PLUGS:
				int[] initialPositions = setting.getInitialPositions();
				
				if(initialPositions == null)
				{
					System.err.println("The positions array is null, using the default values (1,1,1).");
					initialPositions = new int[] {1,1,1};
				}
				
				ArrayList<BombeRotor> rotors = new ArrayList<BombeRotor>();
				
				//Create new rotors based on the letters we already know were used by the machine.
				for(char c : setting.getUsedLetters())
				{
					rotors.add(new BombeRotor(c));
				}
				
				for(BombeRotor rotor : rotors)
				{
					for(BombeRotor rotorB : rotors)
					{
						if(!rotorB.equals(rotor))
						{
							rotor.removeCombinations(rotorB.getFirstLetter());
						}
					}
				}
				
				for(int i = 1;i < rotors.size(); i++)
				{
					rotors.get(i).setPreviousRotor(rotors.get(i-1));
				}
				
				int rotorsSize = rotors.size();
				
				//Pass through all the possible combinations using rotors (like the EnigmaMachine rotors), move to the next combination
				//when you rotate, clear the plugboard and reset the positions before trying a new combination
				while(rotors.get(0).rotationCompleted() == false)
				{
					machine.clearPlugboard();
					
					for(int i = 0; i < 3; i++)
					{
						machine.setPosition(i, initialPositions[i]);
					}
					
					String decryptedMessage = "";
					
					for(int i = 0 ;i < rotors.size(); i++) 
					{
						BombeRotor rotor = rotors.get(i);
						//Try to add the current combination to the plugboard, if not move to the next one
						while(machine.getPlugboard().addPlug(rotor.getFirstLetter(), rotor.getSecondLetter()) == false)
						{
							rotor.rotate();
						}
					}
					
					decryptedMessage = machine.encodeString(message);
					
					//Move to the next combination
					rotors.get(rotorsSize - 1).rotate();
					
					if(decryptedMessage.contains(word))
					{
						possibleDecryptedMessages.add(decryptedMessage);
					}
				}
			break;
			case MISSING_POSITION:
				for(int i=0;i<26;i++)
				{
					for(int j=0;j<26;j++)
					{
						for(int z=0;z<26;z++)
						{
							machine.setPosition(0, i);
							machine.setPosition(1, j);
							machine.setPosition(2, z);
							
							String decryptedMessage = machine.encodeString(message);
							
							if(decryptedMessage.contains(word))
							{
								possibleDecryptedMessages.add(decryptedMessage);
							}
						}
					}
				}
			break;
			case MISSING_TYPE:
				String[] array = new String[] { "I","II","III","IV","V"};
				initialPositions = setting.getInitialPositions();
				
				if(initialPositions == null)
				{
					System.err.println("The positions array is null, using the default values (1,1,1).");
					initialPositions = new int[] {1,1,1};
				}
				
				for(int i=0;i<5;i++)
				{
					for(int j=0;j<5;j++)
					{
						for(int z=0;z<5;z++)
						{
							machine.addRotor(new BasicRotor(array[i]), 0);
							machine.addRotor(new BasicRotor(array[j]), 1);
							machine.addRotor(new BasicRotor(array[z]), 2);
							
							for(int k = 0; k < 3; k++)
							{
								machine.setPosition(k, initialPositions[k]);
							}
							
							String decryptedMessage = machine.encodeString(message);
							
							if(decryptedMessage.contains(word))
							{
								possibleDecryptedMessages.add(decryptedMessage);
							}
						}
					}
				}
			break;
		}
		return possibleDecryptedMessages;
	}
}

