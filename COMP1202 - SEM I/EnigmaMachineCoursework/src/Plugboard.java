import java.util.HashSet;

public class Plugboard {

	private HashSet<Plug> plugs;
	
	public Plugboard()
	{
		plugs = new HashSet<Plug>(); //instantiate a new , empty set
	}
	/**
	 * Add a plug to the plugboard if it doesn't clash with any of the existing plugs.
	 * The maximum size of the set is 13. ( 13 pairs of 2 letter = 26 letters)
	 * @param end1 first end of the new plug 
	 * @param end2 second end of the new plug
	 * @return	a boolean depending on whether the plug was added or not
	 */
	public boolean addPlug(char end1, char end2)
	{
		Plug newPlug = new Plug(end1, end2); //create a new plug with the provided ends
		
		for(Plug currentPlug : plugs)
		{
			if(currentPlug.clashesWith(newPlug)) //check if the new plug clashesWith any of the existing plugs
				return false; 
		}
		
		plugs.add(newPlug); 
		return true;
	}
	
	/**
	 * Returns the size of the <i>plugs</i> set.
	 * @return	an integer representing the number of plugs currently in the plugboard
	 */
	public int getNumPlugs()
	{
		return plugs.size();
	}
	/**
	 * Clear all the elements (plugs) in the <i>plugs</i> set
	 */
	public void clear()
	{
		plugs.clear();
	}
	
	/**
	 * Substitute the character if there is a plug to do so.
	 * @param letterIn a character to substitute 
	 * @return	the substituted letter or the unchanged parameter <i>letterIn</i>
	 */
	public char substitute(char letterIn)
	{
		letterIn = Character.toUpperCase(letterIn); //make sure the letterIn is uppercase
		
		for(Plug currentPlug : plugs) 
		{
			char sub = currentPlug.encode(letterIn); 
			if(sub != letterIn) //check if the letter was substituted or not
				return sub; 
		}
		
		return letterIn; //return the unchanged value 
	}
}