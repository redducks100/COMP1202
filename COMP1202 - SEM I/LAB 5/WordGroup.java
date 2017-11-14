import java.util.HashSet;
import java.util.HashMap;

public class WordGroup 
{
	
	private String words;
	
	/**
		Public constructor for the WordGroup class.
		It converts all the letters in the parameter to lowercase.
		@param words a string containing a sentence of some kind
	*/
	public WordGroup(String words)
	{
		this.words = words.toLowerCase();
	}
	
	/**
		Returns an array which contains all the words in the string.
		@return String[] an string array containing all the words
	*/
	public String[] getWordArray()
	{
		return words.split(" ");
	}
	
	/**
		Return a hashset containing all words and no duplicates from this and the give wordgroup.
		@param group another group to get words from
		@return HashSet<String> return the created set from the 2 given groups
	*/
	public HashSet<String> getWordSet(WordGroup group)
	{
		HashSet<String> set = new HashSet<String>();
		String[] words1 = this.getWordArray();
		String[] words2 = group.getWordArray();
		
		//from the nature of hashsets we don't need to check if an word already exists as an hashset will not contain duplicates
		
		for(int i=0;i<words1.length;i++) //iterate through the first group and add all the words
		{
			set.add(words1[i]);
		}
		
		for(int i=0;i<words2.length;i++) //iterate through the second group and add all the words
		{
			set.add(words2[i]);
		}
		
		return set;
	}
	
	/**
		Return a hashmap containing how many times does each word appear in the string
		@return HashMap<String, Integer> return the created map containing the count for each word
	*/
	public HashMap<String, Integer> getWordCounts()
	{
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] words = this.getWordArray();
		
		for(int i=0;i<words.length;i++) //iterate through the word array
		{
			if(!map.containsKey(words[i])) //if the map does not contain the word, add it with a count of 1
			{
				map.put(words[i], 1);
			}
			else //if the map does contain the word, get the current count, increase it by 1 and add it back to the map
			{
				int count = map.get(words[i])+1; 
				map.put(words[i], count);
			}
		}
		
		return map;
	}
	
}