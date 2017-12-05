import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FlashCardReader
{
	private BufferedReader reader;
	
	public FlashCardReader()
	{
		try {
			reader = new BufferedReader(new FileReader("Questions.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("File Questions.txt is not found in the project directory!");
		}
	}
	
	/**
	 * @return the next line in the file
	 */
	public String getLine() {
		String nextLine = null;
		try {
			nextLine = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nextLine;
	}
	
	/**
	 * @return the state of the bufferedReader
	 */
	public boolean isFileReady()
	{
		try {
			return reader.ready();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @return a new ArrayList of FlashCards created from the file
	 */
	public ArrayList<FlashCard> getFlashCards()
	{
		ArrayList<FlashCard> flashCards = new ArrayList<FlashCard>();
		String nextLine = getLine();
		while(nextLine != null)
		{
			String question = nextLine.split(":")[0];
			String answer = nextLine.split(":")[1];
			
			FlashCard newCard = new FlashCard(question,answer);
			flashCards.add(newCard);
			
			nextLine = getLine();
		}
		
		return flashCards;
	}
}