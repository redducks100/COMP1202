public class Plug {
	
	private char end1, end2;
	
	public Plug(char end1, char end2)
	{
		//always make sure the letters are uppercase
		this.end1 = Character.toUpperCase(end1); 
		this.end2 = Character.toUpperCase(end2); 
	}
	
	/**
	 * Get method for the end1 variable
	 * @return	the first end of the plug, a char 
	 */
	public char getEnd1()
	{
		return end1;
	}
	
	/**
	 * Set method for the end1 variable
	 * @param end1 a char representing an end of the plug 
	 */
	public void setEnd1(char end1)
	{
		this.end1 = Character.toUpperCase(end1); //make sure the char is uppercase
	}
	
	/**
	 * Get method for the end2 variable
	 * @return	the second end of the plug, a char 
	 */
	public char getEnd2()
	{
		return end2;
	}
	
	/**
	 * Set method for the end2 variable
	 * @param end2 a char representing an end of the plug 
	 */
	public void setEnd2(char end2)
	{
		this.end2 = Character.toUpperCase(end2); //make sure the char is uppercase
	}
	
	/**
	 * If the parameter letterIn is equal with one of the ends 
	 * it returns the other end. If not, return letterIn
	 * @param letterIn a char representing which letter to encode 
	 * @return one of the ends or the parameter
	 */
	public char encode(char letterIn)
	{
		letterIn = Character.toUpperCase(letterIn); //make sure the char is uppercase
		
		if(end1 != letterIn && end2 != letterIn)
		{
			return letterIn;
		}
		
		if(end1 == letterIn) return end2;
		
		return end1;
	}
	/**
	 * Check if any ends of the current plug is equal to any 
	 * ends of the parameter plugin
	 * @param plugin a plug to check against
	 * @return a boolean whether it collides with the other plug or not
	 */
	public boolean clashesWith(Plug plugin)
	{
		return (end1 == plugin.getEnd1() || end1 == plugin.getEnd2() || end2 == plugin.getEnd1() || end2 == plugin.getEnd2());
	}
}
