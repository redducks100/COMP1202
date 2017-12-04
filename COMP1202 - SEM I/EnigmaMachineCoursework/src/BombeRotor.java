import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class BombeRotor {
	
	private char knownLetter;
	private BombeRotor previousRotor = null;
	private ArrayList<char[]> possibleCombinations;
	private int currentPosition;
	private boolean completedRotation = false;
	
	public BombeRotor(char knownLetter)
	{
		this.knownLetter = knownLetter;
		this.currentPosition = 0;
		generateCombinations();
	}
	
	/**
	 * Get method for the <i>knownLetter</i> variable.
	 * @return the known letter in the rotor
	 */
	public char getFirstLetter()
	{
		return knownLetter;
	}
	
	/**
	 * Get method for the second letter in the current combination
	 * @return the second letter in the combination
	 */
	public char getSecondLetter()
	{
		return getCurrentCombination()[1];
	}
	
	/**
	 * Get method for the <i>completedRotation</i> variable.
	 * @return the state of the rotor
	 */
	public boolean rotationCompleted()
	{
		return completedRotation;
	}
	
	/**
	 * Set method for the <i>previousRotor</i> variable.
	 * @param previousRotor a bombeRotor
	 */
	public void setPreviousRotor(BombeRotor previousRotor)
	{
		this.previousRotor = previousRotor;
	}
	
	/**
	 * Create all the possible combinations.
	 */
	private void generateCombinations()
	{
		possibleCombinations = new ArrayList<char[]>();
		for(char c ='A';c <= 'Z';c++)
		{
			if(c != knownLetter)
			{
				possibleCombinations.add(new char[] { knownLetter, c});
			}
		}
	}
	
	/**
	 * Remove combinations from the possible list.
	 * @param c what letter the combinations shouldn't have
	 */
	public void removeCombinations(char c)
	{
		for(int i=0;i<possibleCombinations.size();i++)
		{
			if(possibleCombinations.get(i)[1] == c)
			{
				possibleCombinations.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Get the current combination.
	 * @return an array with a combination
	 */
	public char[] getCurrentCombination()
	{
		return possibleCombinations.get(currentPosition);
	}
	
	/**
	 * Rotate the rotor. (next combination)
	 */
	public void rotate()
	{
		currentPosition = currentPosition + 1;
		
		if(currentPosition >= possibleCombinations.size())
		{
			completedRotation = true;
			if(previousRotor != null)
			{
				previousRotor.rotate();
			}
			currentPosition = currentPosition % possibleCombinations.size();
		}
	}
	
}
