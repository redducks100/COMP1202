public class BombeSettings 
{
		private char[] usedLetters;
		private BombeSettingsType type;
		private int[] initialPositions;
		
		/**
		 * Main Constructor
		 * @param type settings should the bomb use
		 * @param usedLetters known letters are there in the plugs
		 * @param initialPositions an array with the starting positions of the rotors
		 * @throws Exception if the usedLetter are null or empty and type is MISSING_PLUGS
		 */
		public BombeSettings(BombeSettingsType type, String usedLetters, int[] initialPositions) throws Exception
		{
			if(type == BombeSettingsType.MISSING_PLUGS)
			{
				if(usedLetters == null || usedLetters.isEmpty())
				{
					throw new Exception("You need to have atleast one letter to start the combinations from!");
				}
				else
				{
					this.usedLetters = usedLetters.toCharArray();
				}
			}
			this.type = type;
			this.initialPositions = initialPositions;
		}
		
		/**
		 * Get method for the <i>type</i> variable.
		 * @return the bombe settings type
		 */
		public BombeSettingsType getType()
		{
			return type;
		}
		
		/**
		 * Get method for the <i>usedLetters</i> variable.
		 * @return the letters array
		 */
		public char[] getUsedLetters()
		{
			return usedLetters;
		}
		
		/**
		 * Get method for the <i>initialPositions</i> variable.
		 * @return the positions array
		 */
		public int[] getInitialPositions()
		{
			return initialPositions;
		}
}
