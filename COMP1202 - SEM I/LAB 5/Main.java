import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

public class Main
{
	public static void main(String[] args)
	{
		//creating the word groups
		WordGroup group1 = new WordGroup("You can discover more about a person in an hour of play than in a year of conversation");
		WordGroup group2 = new WordGroup("When you play play hard when you work dont play at all");
		
		//part1
		String[] group1_array = group1.getWordArray();
		String[] group2_array = group2.getWordArray();
		
		System.out.println("Part 1:");
		
		//print out the content of the first array
		for(int i=0;i<group1_array.length;i++)
		{
			System.out.println(group1_array[i]);
		}
		//print out the content of the second array
		for(int i=0;i<group2_array.length;i++)
		{
			System.out.println(group2_array[i]);
		}
		System.out.println("");
		//part 2
		
		System.out.println("\nPart 2:");
		
		HashSet<String> set = group1.getWordSet(group2);
		
		Iterator setIterator = set.iterator();
		
		//use the iterator and print out the elements from the hashset
		while(setIterator.hasNext())
		{
			System.out.println(setIterator.next());
		}
		System.out.println("");
		//part 3 
		System.out.println("\nPart 3 A:");
		
		//get the word counts from both groups
		HashMap<String, Integer> map1 = group1.getWordCounts();
		HashMap<String, Integer> map2 = group2.getWordCounts();

		//print out the words and the count
		for(String s : map1.keySet())
		{
			System.out.println(s + " " + map1.get(s));
		}
		for(String s : map2.keySet())
		{
			System.out.println(s + " " + map2.get(s));
		}
		
		System.out.println("\nPart 3 B:");
		
		//get the combined set
		HashSet<String> bothSet = group1.getWordSet(group2);
		
		setIterator = bothSet.iterator();
		
		//iterate through the set
		while(setIterator.hasNext())
		{
			String current = setIterator.next().toString();
			int sumCount = 0;
			//check if the current word appears in the first group word count hashmap
			if(map1.containsKey(current))
			{
				sumCount+=map1.get(current); //add the count if it does
			}
			//check if the current word appears in the second group word count hashmap
			if(map2.containsKey(current))
			{
				sumCount+=map2.get(current); //add the count if it does
			}
			System.out.println(current + " " + Integer.toString(sumCount)); //print out the word and the total number of apparitions
		}
		
	}
}