import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Quiz
{
	private FlashCardReader cardReader;
	private ArrayList<FlashCard> flashCards;
	
	public Quiz()
	{
		cardReader = new FlashCardReader();
		flashCards = cardReader.getFlashCards();
		play();
	}
	
	public static void main(String[] args)
	{
		Quiz newQuiz = new Quiz();
	}
	
	/**
	 * Start the quiz.
	 */
	public void play()
	{
		Scanner scanner = new Scanner(System.in);
		String results = "";
		System.out.println("Welcome to the quiz game!");
		
		int numberOfQuestions = flashCards.size();
		int correctAnswers = 0;
		
		for(FlashCard card : flashCards)
		{
			String question = card.getQuestion();
			String correctAnswer = card.getAnswer();
			
			System.out.println(question);
			System.out.println("Type your answer: ");
			
			String userAnswer = scanner.nextLine();
			results += question + userAnswer;
			if(correctAnswer.toLowerCase().equals(userAnswer.toLowerCase()))
			{
				System.out.println("You are correct!");
				results += ",correct";
				correctAnswers++;
			}
			else
			{
				System.out.println("You are wrong! The correct answer is: " + correctAnswer);
				results += ",wrong";
			}
			results += "\r\n";
		}
		System.out.println("Do you want to save the results? Y:Yes N:No");
		String answer = scanner.nextLine();
		
		//Don't let the user pass until it gives a valid answer
		while(!answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("N"))
		{
			System.out.println("Choose (Y)es or (N)o!");
			answer = scanner.nextLine();
		}
		
		if(answer.toUpperCase().equals("Y"))
		{
			//Calculate the percentage and append it to the results
			results += "Score: " + ((correctAnswers * 100) / numberOfQuestions);
			save(results);
		}
		
		System.out.println("Thank you for playing!");
	}
	
	/**
	 * Save the results to the "save.txt" file
	 * @param results content to save
	 */
	private void save(String results)
	{
		PrintStream stream;
		try {
			stream = new PrintStream(new File("save.txt"));
			stream.write(results.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}